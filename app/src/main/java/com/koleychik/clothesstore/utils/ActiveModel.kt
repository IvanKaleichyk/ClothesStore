package com.koleychik.clothesstore.utils

import com.koleychik.clothesstore.models.ProductModel
import javax.inject.Inject

class ActiveModel @Inject constructor() {
    var model: ProductModel? = null
}