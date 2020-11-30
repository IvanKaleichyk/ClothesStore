package com.koleychik.clothesstore.utils

import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.utils.constants.Constants

fun checkMinAndMaxPrice(minValue: Int, maxValue: Int): Int? {
    return when {
        maxValue < minValue -> R.string.errorMinMoreThanMax
        maxValue > Constants.priceMax -> R.string.tooBigMaxValue
        minValue < Constants.priceMin -> R.string.tooLitleMinPrice
        else -> null
    }
}