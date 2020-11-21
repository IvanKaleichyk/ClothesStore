package com.koleychik.clothesstore.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.koleychik.clothesstore.database.dao.HistoryDao
import com.koleychik.clothesstore.models.HistoryModel

@Database(entities = [HistoryModel::class], version = 1)
abstract class HistoryDatabase: RoomDatabase() {

    abstract fun mainDAO(): HistoryDao

//    companion object {
//        @Volatile
//        private var instance: HistoryDatabase? = null
//
//        fun getInstance(context: Context): HistoryDatabase? {
//            if (instance == null) {
//                synchronized(HistoryDatabase::class) {
//                    if (instance == null) {
//                        instance = Room.databaseBuilder(
//                            context.applicationContext,
//                            HistoryDatabase::class.java,
//                            "HistoryDatabase"
//                        ).build()
//                    }
//                    return instance
//                }
//            }
//            return instance
//        }
//    }

}