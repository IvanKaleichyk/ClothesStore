package com.koleychik.clothesstore.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.koleychik.clothesstore.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface BasketDao {

    @Query("SELECT * FROM ProductModel")
    suspend fun getAll() : List<ProductModel>

    @Delete
    suspend fun delete(model : ProductModel)

    @Insert
    suspend fun insert(model : ProductModel)

}