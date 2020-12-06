package com.koleychik.clothesstore.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.koleychik.clothesstore.models.networkModels.Photo
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity
@Parcelize
open class ProductModel(
    @PrimaryKey
    val id: Int,
    @Embedded
    val photo: @RawValue Photo,
    val categoryId: Int,
    val price: Int,
    var salePrice: Int,
    var isInBasket: Boolean,
    val isInFavorites: Boolean,
    val transitionName : String,
    var sale : Int? = null
): Parcelable