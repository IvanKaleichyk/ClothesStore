package com.koleychik.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.koleychik.clothesstore.R

//class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

//    private val sortedList: SortedList<Int>
//
//    init {
//        sortedList = SortedList(
//            Int::class.java,
//            object : SortedListAdapterCallback<Int>(this) {
//                override fun compare(o1: Int, o2: Int): Int = 1
//
//                override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
//                    oldItem == newItem
//
//                override fun areItemsTheSame(item1: Int, item2: Int): Boolean =
//                    item1 == item2
//            })
//    }
//
//    fun submitList(newList: List<Int>) {
//        sortedList.clear()
//        sortedList.addAll(newList)
//    }
//
//    fun addToList(value : Int){
//        sortedList.add(value)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
//        return MainViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_rv_message, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//        holder.bind(sortedList[position])
//    }
//
//    override fun getItemCount(): Int = sortedList.size()
//
//    class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//
//        fun bind(model : Int){
//
//        }
//
//    }

//}