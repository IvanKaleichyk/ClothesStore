package com.koleychik.clothesstore.ui.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.koleychik.clothesstore.models.ProductModel

val productCallback = object : DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel) =
        oldItem.photo.idPhoto == newItem.photo.idPhoto

    override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean =
        oldItem.equals(newItem)
}