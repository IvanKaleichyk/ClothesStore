package com.koleychik.clothesstore.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.callbacks.OnClickToHistoryModel
import com.koleychik.clothesstore.models.HistoryModel
import kotlinx.android.synthetic.main.item_history_rv.view.*
import java.lang.Exception
import javax.inject.Inject

class HistoryAdapter @Inject constructor() : RecyclerView.Adapter<HistoryAdapter.MainViewHolder>() {

    private val sortedList: SortedList<HistoryModel>

    private var onClickToHistoryModel: OnClickToHistoryModel? = null

    init {
        sortedList = SortedList(
            HistoryModel::class.java,
            object : SortedListAdapterCallback<HistoryModel>(this) {
                override fun compare(o1: HistoryModel, o2: HistoryModel): Int {
                    return try {
                        (o2.time - o1.time).toInt()
                    }catch (e : Exception){
                        1
                    }
                }

                override fun areContentsTheSame(
                    oldItem: HistoryModel,
                    newItem: HistoryModel
                ): Boolean =
                    oldItem == newItem

                override fun areItemsTheSame(item1: HistoryModel, item2: HistoryModel): Boolean =
                    item1 == item2
            })
    }

    fun submitList(newList: List<HistoryModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }

    fun addToList(value: HistoryModel) {
        sortedList.add(value)
    }

    fun deleteModel(model : HistoryModel){
        sortedList.remove(model)
    }

    fun setOnClick(onClickToHistoryModel: OnClickToHistoryModel){
        this.onClickToHistoryModel = onClickToHistoryModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var model : HistoryModel

        fun bind(model: HistoryModel) {
            this.model = model
            itemView.text.text = model.text
            itemView.setOnClickListener{
                onClickToHistoryModel?.click(model)
            }
        }

    }

}