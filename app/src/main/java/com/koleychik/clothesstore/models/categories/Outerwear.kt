package com.koleychik.clothesstore.models.categories

import android.os.Parcelable
import com.koleychik.clothesstore.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class Outerwear : Category(), Parcelable {
    override fun getId(): Int = 0
    override fun getResourceName(): Int = R.string.outerwear
    override fun getImageResource(): Int = R.drawable.ic_outerwear_icon_svg
    override fun getSearchName(): String = "outerwear"
}