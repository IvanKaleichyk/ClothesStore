package com.koleychik.clothesstore.data

import android.bluetooth.BluetoothClass
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.DeviceImage
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.networkModels.Photo
import com.koleychik.clothesstore.models.networkModels.Urls
import com.koleychik.clothesstore.utils.*
import com.koleychik.clothesstore.utils.constants.Constants
import java.lang.StringBuilder
import java.util.*

fun getListDeviceImages(): List<DeviceImage> {
    val random = Random()
    val idSet = mutableSetOf<Int>()
    val listRes = mutableListOf<DeviceImage>()
    var number = 0
    while (number < 5) {
        val id = random.nextInt()
        if (idSet.contains(id)) continue
        idSet.add(id)
        val data = StringBuilder()
        for (i in (0..9)) data.append(i)
        listRes.add(DeviceImage(id.toString(), data.toString()))
        number++
    }
    return listRes
}

fun getListHistoryModel(): List<HistoryModel> {
    val list = mutableListOf<HistoryModel>()
    val listCategory = getAll()
    val random = Random()
    for (i in (0..5)) {
        list.add(
            generateHistoryModel(
                text = "text_$i",
                categoryId = listCategory[random.nextInt(listCategory.size)].getId(),
                minPrice = Constants.priceMin,
                maxPrice = Constants.priceMax
            )
        )
    }
    return list
}

fun listBasketModels(): List<BasketModel> {
    val listProducts = listProductModels()
    val list = mutableListOf<BasketModel>()
    for (i in listProducts) list.add(
        BasketModel(
            generateId(),
            i.photo,
            i.categoryId,
            i.price,
            i.salePrice,
            i.isInBasket,
            i.isInFavorites,
            "L",
            generateTransitionName(i.photo.urls.regular),
            i.sale
        )
    )
    return list
}

fun listProductModels(): List<ProductModel> {
    val list = mutableListOf<ProductModel>()
    val photos = listPhotos()
    val saleList = salePrices()
    val listInBoolean = listIsInBasket()
    for (i in photos.indices) list.add(
        ProductModel(
            generateId(),
            photos[i],
            i,
            generatePrice(),
            saleList[i],
            listInBoolean[i],
            false,
            generateTransitionName(photos[i].urls.regular),
            saleList[i]
        )
    )
    return list
}

fun listPhotos(): List<Photo> {
    val list = mutableListOf<Photo>()
    for (i in listUrls.indices) list.add(
        Photo(
            "created_at_$i",
            null,
            "id_photo_$i",
            listUrls[i]
        )
    )
    return list
}

val listUrls = listOf(
    generateUrls("https://images.unsplash.com/photo-1606440894295-8a79c32fdaf4?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=334&q=80"),
    generateUrls("https://images.unsplash.com/photo-1606446134254-61235a08ccb6?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=282&q=80"),
    generateUrls("https://images.unsplash.com/photo-1606462531411-bd77abcb7c57?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=750&q=80"),
    generateUrls("https://images.unsplash.com/photo-1606416463636-393d972e14df?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=376&q=80"),
    generateUrls("https://images.unsplash.com/photo-1606323593036-c082bb57561c?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=282&q=80")
)

fun listIsInBasket(): List<Boolean> {
    val list = mutableListOf<Boolean>()
    for (i in listUrls.indices) {
        list.add(i % 2 == 0)
    }
    return list
}

private fun salePrices(): List<Int> {
    val list = mutableListOf<Int>()
    for (i in listUrls.indices) {
        var sale = 0
        if (i % 3 == 0) sale = Random().nextInt(Constants.priceMax - 200) + 200
        list.add(sale)
    }
    return list
}
private fun generateUrls(url: String) = Urls(url, url, url, url, url)