package com.koleychik.clothesstore.utils

import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.categories.Category
import com.koleychik.clothesstore.models.networkModels.Photo
import java.math.BigDecimal
import java.util.*

fun generateHistoryModel(text: String, category: Category, minPrice: Int, maxPrice: Int) =
    HistoryModel(text, Date().time, category, minPrice, maxPrice)

fun generateProductModel(photo: Photo, category: Category) = ProductModel(
    photo = photo,
    category = category,
    price = generatePrice(),
    salePrice = 0,
    isInBasket = false,
    isInFavorites = false
)

fun generateProductModel(photos: List<Photo>, category: Category): List<ProductModel> {
    val list = mutableListOf<ProductModel>()
    for (i in photos) {
        list.add(generateProductModel(i, category))
    }
    return list
}

fun generatePrice(): Int {
    return Random().nextInt(1 + Constants.priceMax - Constants.priceMin) + Constants.priceMin
}

fun generateSalePrice(productModel: ProductModel) {
    val random = Random()
    val percent = listSalePercent[random.nextInt(listSalePercent.size)]
    productModel.salePrice = minusPercent(productModel.price, percent)
    productModel.sale = percent
}

fun minusPercent(valueFull: Int, percent: Int): Int {
    val valueDecimal = BigDecimal(valueFull + percent)
    return valueDecimal.divide(BigDecimal(100), 0).toInt()
}