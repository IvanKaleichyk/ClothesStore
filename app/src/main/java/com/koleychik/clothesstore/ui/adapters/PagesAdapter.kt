package com.koleychik.clothesstore.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.ItemRvPageBinding
import com.koleychik.clothesstore.utils.CheckBoxStyle
import kotlinx.android.synthetic.main.item_rv_page.view.*

class PagesAdapter(private var nowPage: Int, private val onClickToPage: (pageValue: Int) -> Unit) :
    RecyclerView.Adapter<PagesAdapter.MainViewHolder>() {

    private val sortedList: SortedList<Int>

    private val checkBoxStyle = CheckBoxStyle(
        list = mutableListOf(),
        checkedList = mutableListOf(),
        isSingle = true,
        canBeNothingSelect = false
    )

    init {
        sortedList = SortedList(Int::class.java, object : SortedListAdapterCallback<Int>(this) {
            override fun compare(o1: Int, o2: Int): Int = o2 - o1

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(item1: Int, item2: Int): Boolean =
                item1 == item2
        })
    }

    fun submitList(newList: Int) {
        sortedList.clear()
        for (i in (0..newList)){
            sortedList.add(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val holder = MainViewHolder(
            layoutInflater.inflate(R.layout.item_rv_page, parent, false),
            ItemRvPageBinding.inflate(layoutInflater)
        )

        checkBoxStyle.addToList(holder.itemView.number)
        return holder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    inner class MainViewHolder(itemView: View, private val binding: ItemRvPageBinding) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(model: Int) {
            if (model == nowPage) checkBoxStyle.setChecked(itemView.number)

            binding.number.apply {
                text = model.toString()
                setOnClickListener {
                    onClickToPage(model)
                    nowPage = model
                    checkBoxStyle.setChecked(binding.number)
                }
            }
        }

    }

}