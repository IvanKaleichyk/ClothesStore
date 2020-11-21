package com.koleychik.clothesstore.repositories

import com.koleychik.clothesstore.database.database.BoughtDatabase
import com.koleychik.clothesstore.database.database.HistoryDatabase
import com.koleychik.clothesstore.models.ProductModel

class BoughtRepository (database : BoughtDatabase) {

    val mainDao = database.mainDAO()

    suspend fun getAll() = mainDao.getAll()

    suspend fun insert(model : ProductModel) = mainDao.insert(model)

    suspend fun delete(model : ProductModel) = mainDao.delete(model)

}