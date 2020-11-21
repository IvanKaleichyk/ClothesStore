package com.koleychik.clothesstore.ui.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import coil.load
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.ItemRvProductBinding
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.utils.getCurrencyString
import javax.inject.Inject


class ProductAdapter @Inject constructor(): RecyclerView.Adapter<ProductAdapter.MainViewHolder>() {

    private val sortedList: SortedList<ProductModel>

    init {
        sortedList = SortedList(
            ProductModel::class.java,
            object : SortedListAdapterCallback<ProductModel>(this) {
                override fun compare(o1: ProductModel, o2: ProductModel): Int = o2.id - o1.id

                override fun areContentsTheSame(
                    oldItem: ProductModel,
                    newItem: ProductModel
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areItemsTheSame(item1: ProductModel, item2: ProductModel): Boolean =
                    item1 == item2
            })
    }

    fun submitList(newList: List<ProductModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }

    fun addToList(value: ProductModel) {
        sortedList.add(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)!!

        return MainViewHolder(
            layoutInflater.inflate(R.layout.item_rv_product, parent, false),
            ItemRvProductBinding.inflate(layoutInflater)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    class MainViewHolder(itemView: View, private val binding: ItemRvProductBinding) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(model: ProductModel) {
            binding.description.text = model.photo.description
            binding.fullPrice.text = getCurrencyString(model.price)
            binding.img.load(model.photo.urls?.regular)
            if (model.sale != null) {
                binding.fullPrice.paintFlags =
                    binding.fullPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.salePrice.visibility = View.VISIBLE
                binding.salePrice.text = getCurrencyString(model.sale!!)
            } else {
                binding.fullPrice.paintFlags = binding.fullPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                binding.salePrice.visibility = View.GONE
            }
        }
    }


}