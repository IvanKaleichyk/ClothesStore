package com.koleychik.clothesstore.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.models.DeviceImage
import kotlinx.android.synthetic.main.item_rv_img.view.*
import javax.inject.Inject

class ImagesAdapter @Inject constructor() : RecyclerView.Adapter<ImagesAdapter.MainViewHolder>() {

    var list = listOf<DeviceImage>()

    lateinit var onCLickToItem: OnCLickToItem

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_img, parent, false)
        )

    override fun getItemCount(): Int = list.size

    fun submitList(newList : List<DeviceImage>, onCLickToItem: OnCLickToItem){
        list = newList
        this.onCLickToItem = onCLickToItem
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: DeviceImage) {
            itemView.img.apply {
                load(model.data)
                setOnClickListener { onCLickToItem.click(model.data) }
            }
        }
    }
}

interface OnCLickToItem{
   fun click(data: String)
}