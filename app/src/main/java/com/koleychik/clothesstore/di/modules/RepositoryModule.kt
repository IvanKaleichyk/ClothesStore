package com.koleychik.clothesstore.di.modules

import com.koleychik.clothesstore.database.database.*
import com.koleychik.clothesstore.repositories.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    fun provideBasketRepository(database: BasketDatabase) = BasketRepository(database)

    @Provides
    @Singleton
    fun provideBoughtRepository(database: BoughtDatabase) = BoughtRepository(database)

    @Provides
    @Singleton
    fun provideHistoryRepository(database: HistoryDatabase) = HistoryRepository(database)

    @Provides
    @Singleton
    fun provideSaleRepository(database: SaleDatabase) = SaleRepository(database)

    @Provides
    @Singleton
    fun provideFirebaseRepository() = FirebaseRepository()
}