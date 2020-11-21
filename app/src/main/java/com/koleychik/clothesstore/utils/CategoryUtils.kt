package com.koleychik.clothesstore.utils

import android.content.Context
import com.koleychik.clothesstore.models.categories.*

fun getNamesResource(): List<Int> {
    val list = getAll()
    val listResult = mutableListOf<Int>()
    for (i in list){
        listResult.add(i.getResourceName())
    }
    return listResult
}

fun getCategoryById(id: Int): Category {
    val list = getAll()
    for (i in list) {
        if (i.getId() == id) return i
    }
    return Outerwear()
}

fun getAll() = listOf<Category>(
    Outerwear(),
    Footwear(),
    Pants(),
    Accessories()
)