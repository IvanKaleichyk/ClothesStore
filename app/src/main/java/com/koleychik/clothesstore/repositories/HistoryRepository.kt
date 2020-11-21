package com.koleychik.clothesstore.repositories

import com.koleychik.clothesstore.database.database.HistoryDatabase
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.models.ProductModel

class HistoryRepository(database : HistoryDatabase) {

    val mainDao = database.mainDAO()

    suspend fun getAll() = mainDao.getAll()

    suspend fun insert(model : HistoryModel) = mainDao.insert(model)

    suspend fun delete(model : HistoryModel) = mainDao.delete(model)
}