package com.koleychik.clothesstore.database.dao

import androidx.room.*
import com.koleychik.clothesstore.models.HistoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryModel")
    suspend fun getAll() : List<HistoryModel>

    @Delete
    suspend fun delete(model : HistoryModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: HistoryModel)

}