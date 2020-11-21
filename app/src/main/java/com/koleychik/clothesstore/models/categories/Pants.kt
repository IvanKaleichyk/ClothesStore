package com.koleychik.clothesstore.models.categories

import android.os.Parcelable
import com.koleychik.clothesstore.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pants : Category(), Parcelable {
    override fun getId(): Int = 2
    override fun getResourceName(): Int = R.string.pants
    override fun getImageResource(): Int = 0
    override fun getSearchName(): String = "pants"
}