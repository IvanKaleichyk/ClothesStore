package com.koleychik.clothesstore.ui.adapters

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import coil.load
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.ItemRvProductBinding
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.categories.Category
import com.koleychik.clothesstore.utils.ActiveModel
import com.koleychik.clothesstore.utils.adaptersHelpers.ProductAdapterHelper
import com.koleychik.clothesstore.utils.constants.ProductConstants
import com.koleychik.clothesstore.utils.getCurrencyString
import kotlinx.android.synthetic.main.item_rv_product.view.*
import javax.inject.Inject


class ProductAdapter @Inject constructor(private val onClick: (imageView: ImageView, model: ProductModel) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.MainViewHolder>() {

    private val sortedList = SortedList(
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

    private val listCategoryId = mutableListOf<Int>()

    private val helper by lazy {
        ProductAdapterHelper(sortedList, listCategoryId)
    }

    fun submitList(newList: List<ProductModel>) {
        sortedList.clear()
        for (i in newList) helper.addToList(i)
    }

    fun updateList(newList: List<ProductModel>) {
        for (i in newList) if (!listCategoryId.contains(i.id)) helper.addToList(i)
        helper.removeUnusedItems(newList)
    }

    fun isSortedListEmpty() = sortedList.size() <= 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)!!

        return MainViewHolder(
            layoutInflater.inflate(R.layout.item_rv_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    inner class MainViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        fun bind(model: ProductModel) {
            itemView.description.text = model.photo.description
            itemView.fullPrice.text = getCurrencyString(model.price)
            itemView.img.load(model.photo.urls.regular)
            if (model.sale != null) {
//              cross out
                itemView.fullPrice.paintFlags =
                    itemView.fullPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                itemView.salePrice.visibility = View.VISIBLE
                itemView.salePrice.text = getCurrencyString(model.sale!!)
            } else {
//              destroy  cross out
                itemView.fullPrice.paintFlags =
                    itemView.fullPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                itemView.salePrice.visibility = View.GONE
            }

            itemView.setOnClickListener {
//                activeModel.model = model
//                val bundle = Bundle()
//                bundle.putBoolean(ProductConstants.comeFromAnother, true)
//                Navigation.findNavController(it)
//                    .navigate(R.id.action_navDrawerFragment_to_productFragment, bundle)
                onClick(itemView.img, model)
            }
        }
    }


}