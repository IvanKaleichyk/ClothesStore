package com.koleychik.clothesstore.data

import com.koleychik.clothesstore.database.database.BasketDatabase
import com.koleychik.clothesstore.database.database.BoughtDatabase
import com.koleychik.clothesstore.database.database.HistoryDatabase
import com.koleychik.clothesstore.fakeDI.FakeBasketDao
import com.koleychik.clothesstore.fakeDI.FakeHistoryDao
import com.koleychik.clothesstore.fakeDI.FakeProductDao
import com.koleychik.clothesstore.repositories.BasketRepository
import com.koleychik.clothesstore.repositories.BoughtRepository
import com.koleychik.clothesstore.repositories.HistoryRepository
import com.koleychik.clothesstore.repositories.NetworkRepository
import org.mockito.Mockito
import retrofit2.Response

class DataRepository {

    companion object{
//        suspend fun getNetworkRepository(): NetworkRepository{
//            val repository = Mockito.mock(NetworkRepository::class.java)
//            Mockito.`when`(repository.search(Mockito.any(), Mockito.any())).thenReturn(Response.listPhotos())
//        }

        fun getHistoryRepository(): HistoryRepository{
            val database = Mockito.mock(HistoryDatabase::class.java)
            Mockito.`when`(database.mainDAO()).thenReturn(FakeHistoryDao())
            return HistoryRepository(database)
        }

        fun getBoughtRepository(): BoughtRepository{
            val database = Mockito.mock(BoughtDatabase::class.java)
            Mockito.`when`(database.mainDAO()).thenReturn(FakeProductDao())
            return BoughtRepository(database)
        }

        fun getBasketRepository(): BasketRepository{
            val database = Mockito.mock(BasketDatabase::class.java)
            Mockito.`when`(database.mainDAO()).thenReturn(FakeBasketDao())
            return BasketRepository(database)
        }
    }

}