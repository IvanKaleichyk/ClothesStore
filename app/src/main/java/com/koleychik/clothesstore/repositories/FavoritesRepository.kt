package com.koleychik.clothesstore.repositories

import com.koleychik.clothesstore.database.database.FavoritesDatabase
import com.koleychik.clothesstore.database.database.HistoryDatabase
import com.koleychik.clothesstore.models.ProductModel

class FavoritesRepository (database : FavoritesDatabase) {

    val mainDao = database.mainDAO()

    suspend fun getAll() = mainDao.getAll()

    suspend fun insert(model : ProductModel) = mainDao.insert(model)

    suspend fun delete(model : ProductModel) = mainDao.delete(model)


}