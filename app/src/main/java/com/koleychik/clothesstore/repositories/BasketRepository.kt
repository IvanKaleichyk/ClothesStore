package com.koleychik.clothesstore.repositories

import com.koleychik.clothesstore.database.database.BasketDatabase
import com.koleychik.clothesstore.database.database.HistoryDatabase
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.ProductModel

class BasketRepository (database : BasketDatabase) {

    val mainDao = database.mainDAO()

    suspend fun getAll() = mainDao.getAll()

    suspend fun insert(model : BasketModel) = mainDao.insert(model)

    suspend fun delete(model : BasketModel) = mainDao.delete(model)

    suspend fun getById(id : Int) = mainDao.getById(id)

}