package com.koleychik.clothesstore.di.modules

import android.content.Context
import androidx.room.Room
import com.koleychik.clothesstore.database.database.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideHistoryDatabase(context: Context) = Room
        .databaseBuilder(context, HistoryDatabase::class.java, "HistoryDatabase")
        .build()

    @Provides
    @Singleton
    fun provideBasketDatabase(context: Context) = Room
        .databaseBuilder(context, BasketDatabase::class.java, "BasketDatabase")
        .build()

    @Provides
    @Singleton
    fun provideBoughtDatabase(context: Context) = Room
        .databaseBuilder(context, BoughtDatabase::class.java, "BoughtDatabase")
        .build()

    @Provides
    @Singleton
    fun provideSaleDatabase(context: Context) = Room
        .databaseBuilder(context, SaleDatabase::class.java, "SaleDatabase")
        .build()
}