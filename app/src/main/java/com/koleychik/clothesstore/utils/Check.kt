package com.koleychik.clothesstore.utils

import com.koleychik.clothesstore.R

fun checkMinAndMaxPrice(minValue: Int, maxValue: Int): Int? {
    return when {
        maxValue < minValue -> R.string.errorMinMoreThanMax
        maxValue > 1000 -> R.string.tooBigMaxValue
        minValue < 50 -> R.string.tooLitleMinPrice
        else -> null
    }
}