package com.koleychik.clothesstore.models.networkModels

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Photo(
//    val color: String,
    val created_at: String,
    val description: String,
    @SerializedName("id")
    val idPhoto: String,
    @Embedded
    val urls: Urls?
)