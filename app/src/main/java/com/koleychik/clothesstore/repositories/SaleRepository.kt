package com.koleychik.clothesstore.repositories

import com.koleychik.clothesstore.database.database.SaleDatabase
import com.koleychik.clothesstore.models.ProductModel

class SaleRepository(database : SaleDatabase) {

    val mainDao = database.mainDAO()

    suspend fun getAll() = mainDao.getAll()

    suspend fun insert(model : ProductModel) = mainDao.insert(model)

    suspend fun delete(model : ProductModel) = mainDao.delete(model)


}