package com.koleychik.clothesstore.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM BasketModel")
    suspend fun getAll() : List<BasketModel>

    @Query("SELECT * FROM BasketModel WHERE id =:id LIMIT 1")
    suspend fun getById(id : Int) : BasketModel?

    @Delete
    suspend fun delete(model : BasketModel)

    @Insert
    suspend fun insert(model : BasketModel)

}