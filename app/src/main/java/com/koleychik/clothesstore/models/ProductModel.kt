package com.koleychik.clothesstore.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.koleychik.clothesstore.models.networkModels.Photo

@Entity
open class ProductModel(
    @Embedded
    val photo: Photo,
    val categoryId: Int,
    val price: Int,
    var salePrice: Int,
    var isInBasket: Boolean,
    val isInFavorites: Boolean,
    var sale : Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}