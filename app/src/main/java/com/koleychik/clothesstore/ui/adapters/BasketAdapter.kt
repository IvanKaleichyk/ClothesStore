package com.koleychik.clothesstore.ui.adapters

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import coil.clear
import coil.load
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.utils.ActiveModel
import com.koleychik.clothesstore.utils.constants.ProductConstants
import com.koleychik.clothesstore.utils.getCurrencyString
import kotlinx.android.synthetic.main.item_rv_basket.view.*

class BasketAdapter(
    private val activeModel: ActiveModel,
    private val delete: (model: BasketModel) -> Unit
) :
    RecyclerView.Adapter<BasketAdapter.MainViewHolder>() {

    private val sortedList: SortedList<BasketModel>

    init {
        sortedList = SortedList(
            BasketModel::class.java,
            object : SortedListAdapterCallback<BasketModel>(this) {
                override fun compare(o1: BasketModel, o2: BasketModel): Int = o2.id - o1.id

                override fun areContentsTheSame(
                    oldItem: BasketModel,
                    newItem: BasketModel
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areItemsTheSame(item1: BasketModel, item2: BasketModel): Boolean =
                    item1 == item2
            })
    }

    fun submitList(newList: List<BasketModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }

    fun addToList(value: BasketModel) {
        sortedList.add(value)
    }

    fun delete(model: BasketModel) = sortedList.remove(model)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_basket, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    override fun onViewRecycled(holder: MainViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.img.clear()
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: BasketModel) {
            itemView.img.load(model.photo.urls.regular)
            itemView.description.text = model.photo.description
            itemView.size.text = model.size
            itemView.fullPrice.text = model.price.toString()
            if (model.sale != null) {
                itemView.fullPrice.paintFlags =
                    itemView.fullPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                itemView.salePrice.visibility = View.VISIBLE
                itemView.salePrice.text = getCurrencyString(model.sale!!)
            } else {
                itemView.fullPrice.paintFlags =
                    itemView.fullPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                itemView.salePrice.visibility = View.GONE
            }
            setOnClickListener(model)
        }

        private fun setOnClickListener(model: BasketModel) {
            itemView.delete.setOnClickListener {
                delete(model)
            }
            itemView.setOnClickListener {
                activeModel.model = model
                val bundle = Bundle()
                bundle.putBoolean(ProductConstants.comeFromBasket, true)
                Navigation.findNavController(it)
                    .navigate(R.id.action_navDrawerFragment_to_productFragment, bundle)
            }
        }

    }

}