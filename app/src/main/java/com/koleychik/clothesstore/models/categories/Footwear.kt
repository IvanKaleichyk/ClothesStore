package com.koleychik.clothesstore.models.categories

import android.os.Parcelable
import com.koleychik.clothesstore.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class Footwear : Category(), Parcelable {
    override fun getId(): Int = 1
    override fun getResourceName(): Int = R.string.footwear
    override fun getImageResource(): Int = R.drawable.ic_foot_wear_icon_svg

    override fun getSearchName(): String = "footwear"
}