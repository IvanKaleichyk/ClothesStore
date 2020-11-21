package com.koleychik.clothesstore.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.koleychik.clothesstore.models.categories.Category

@Entity
data class HistoryModel(
    val text: String,
    var time: Long,
    @Embedded
    val category: Category,
    val minPrice: Int,
    val maxPrice: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}