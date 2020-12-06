package com.koleychik.clothesstore.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.koleychik.clothesstore.models.networkModels.Photo
import kotlinx.android.parcel.Parcelize

@Entity
class BasketModel(
    id: Int,
    photo: Photo,
    categoryId: Int,
    price: Int,
    salePrice: Int,
    isInBasket: Boolean,
    isInFavorites: Boolean,
    transitionName: String,
    val size: String,
    sale: Int? = null,
) : ProductModel(
    id,
    photo,
    categoryId,
    price,
    salePrice,
    isInBasket,
    isInFavorites,
    transitionName,
    sale
) {
    @PrimaryKey(autoGenerate = true)
    var pk: Int = 0
}
