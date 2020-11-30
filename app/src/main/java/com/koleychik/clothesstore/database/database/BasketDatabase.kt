package com.koleychik.clothesstore.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.koleychik.clothesstore.database.dao.BasketDao
import com.koleychik.clothesstore.database.dao.ProductsDao
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.ProductModel
import javax.inject.Inject

@Database(entities = [BasketModel::class], version = 1)
abstract class BasketDatabase : RoomDatabase(){

    abstract fun mainDAO() : BasketDao

//    companion object {
//
//        @Volatile
//        private var instance: BasketDatabase? = null
//
//        fun getInstance(context: Context): BasketDatabase? {
//            if (instance == null) {
//                synchronized(BasketDatabase::class) {
//                    if (instance == null) {
//                        instance = Room.databaseBuilder(
//                            context.applicationContext,
//                            BasketDatabase::class.java,
//                            "BasketDatabase"
//                        ).build()
//                    }
//                    return instance
//                }
//            }
//            return instance
//        }
//    }

}