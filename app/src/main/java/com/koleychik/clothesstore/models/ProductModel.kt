package com.koleychik.clothesstore.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.koleychik.clothesstore.models.networkModels.Photo
import com.koleychik.clothesstore.models.categories.Category

@Entity
data class ProductModel(
    @Embedded
    val photo: Photo,
    @Embedded
    val category: Category,
    val price: Int,
    var salePrice: Int,
    val isInBasket: Boolean,
    val isInFavorites: Boolean,
    var sale : Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}