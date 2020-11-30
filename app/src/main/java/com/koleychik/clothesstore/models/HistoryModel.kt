package com.koleychik.clothesstore.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryModel(
    val text: String,
    var time: Long,
    val categoryId: Int,
    val minPrice: Int,
    val maxPrice: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}