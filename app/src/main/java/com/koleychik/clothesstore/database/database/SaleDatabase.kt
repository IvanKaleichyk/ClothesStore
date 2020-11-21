package com.koleychik.clothesstore.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.koleychik.clothesstore.database.dao.ProductsDao
import com.koleychik.clothesstore.database.dao.SaleDao
import com.koleychik.clothesstore.models.ProductModel
import javax.inject.Inject

@Database(entities = [ProductModel::class], version = 1)
abstract class SaleDatabase: RoomDatabase(){

    abstract fun mainDAO() : ProductsDao

//    companion object {
//
//        @Volatile
//        private var instance: SaleDatabase? = null
//
//        fun getInstance(context: Context): SaleDatabase? {
//            if (instance == null) {
//                synchronized(SaleDatabase::class) {
//                    if (instance == null) {
//                        instance = Room.databaseBuilder(
//                            context.applicationContext,
//                            SaleDatabase::class.java,
//                            "SaleDatabase"
//                        ).build()
//                    }
//                    return instance
//                }
//            }
//            return instance
//        }
//    }

}