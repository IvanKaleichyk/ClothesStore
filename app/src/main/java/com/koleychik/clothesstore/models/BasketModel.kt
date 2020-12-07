package com.koleychik.clothesstore.models

import androidx.room.Entity
import com.koleychik.clothesstore.models.networkModels.Photo

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
)