package com.koleychik.clothesstore.models

import androidx.room.Entity
import com.koleychik.clothesstore.models.networkModels.Photo

@Entity
class BasketModel(
    photo: Photo,
    categoryId: Int,
    price: Int,
    salePrice: Int,
    isInBasket: Boolean,
    isInFavorites: Boolean,
    val size : String,
    sale: Int? = null,
) : ProductModel(photo, categoryId, price, salePrice, isInBasket, isInFavorites, sale)