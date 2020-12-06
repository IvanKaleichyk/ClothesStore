package com.koleychik.clothesstore.utils

import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.networkModels.Photo
import com.koleychik.clothesstore.utils.constants.Constants
import java.math.BigDecimal
import java.util.*

fun generateBasketModel(
    productModel: ProductModel,
    size: String,
    isInBasket: Boolean = productModel.isInBasket
) = BasketModel(
    generateId(),
    productModel.photo,
    productModel.categoryId,
    productModel.price,
    productModel.salePrice,
    isInBasket,
    productModel.isInFavorites,
    size,
    generateTransitionName(productModel.photo.urls.regular),
    productModel.sale
)

fun generateHistoryModel(text: String, categoryId: Int, minPrice: Int, maxPrice: Int) =
    HistoryModel(text, Date().time, categoryId, minPrice, maxPrice)

fun generateProductModel(
    photo: Photo,
    categoryId: Int,
    minPrice: Int = Constants.priceMin,
    maxPrice: Int = Constants.priceMax
) = ProductModel(
    generateId(),
    photo = photo,
    categoryId = categoryId,
    price = generatePrice(minPrice, maxPrice),
    salePrice = 0,
    isInBasket = false,
    isInFavorites = false,
    generateTransitionName(photo.urls.regular)
)

fun generateProductModel(
    photos: List<Photo>,
    categoryId: Int,
    minPrice: Int = Constants.priceMin,
    maxPrice: Int = Constants.priceMax
): List<ProductModel> {
    val list = mutableListOf<ProductModel>()
    for (i in photos) {
        list.add(generateProductModel(i, categoryId, minPrice, maxPrice))
    }
    return list
}

fun generatePrice(minPrice: Int = Constants.priceMin, maxPrice: Int = Constants.priceMax): Int {
    return Random().nextInt(1 + maxPrice - minPrice) + minPrice
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

fun generateTransitionName(url: String) = url + "_" + Random().nextInt()

fun generateId(): Int {
    var res = 0
    val random = Random()
    for (i in (0..5)) {
        res += random.nextInt(32767)
    }
    return res
}