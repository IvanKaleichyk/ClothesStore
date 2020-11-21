package com.koleychik.clothesstore.models.categories

import android.os.Parcelable
import com.koleychik.clothesstore.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class Accessories : Category(), Parcelable{
    override fun getId(): Int = 3
    override fun getResourceName(): Int = R.string.accessories
    override fun getImageResource(): Int = R.drawable.ic_accessories_icon_svg

    override fun getSearchName(): String = "accessories"
}