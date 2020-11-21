package com.koleychik.clothesstore.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.koleychik.clothesstore.database.dao.ProductsDao
import com.koleychik.clothesstore.models.ProductModel
import javax.inject.Inject

@Database(entities = [ProductModel::class], version = 1)
abstract class BoughtDatabase: RoomDatabase(){

    abstract fun mainDAO() : ProductsDao

//    companion object {
//
//        @Volatile
//        private var instance: BoughtDatabase? = null
//
//        fun getInstance(context: Context): BoughtDatabase? {
//            if (instance == null) {
//                synchronized(BoughtDatabase::class) {
//                    if (instance == null) {
//                        instance = Room.databaseBuilder(
//                            context.applicationContext,
//                            BoughtDatabase::class.java,
//                            "BoughtDatabase"
//                        ).build()
//                    }
//                    return instance
//                }
//            }
//            return instance
//        }
//    }

}