package com.koleychik.clothesstore.fakeDI

import com.koleychik.clothesstore.data.getListHistoryModel
import com.koleychik.clothesstore.data.listBasketModels
import com.koleychik.clothesstore.data.listProductModels
import com.koleychik.clothesstore.database.dao.BasketDao
import com.koleychik.clothesstore.database.dao.HistoryDao
import com.koleychik.clothesstore.database.dao.ProductsDao
import com.koleychik.clothesstore.database.database.BasketDatabase
import com.koleychik.clothesstore.database.database.BoughtDatabase
import com.koleychik.clothesstore.database.database.HistoryDatabase
import com.koleychik.clothesstore.database.database.SaleDatabase
import com.koleychik.clothesstore.di.modules.RepositoryModule
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.models.ProductModel
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton


//class FakeRepositoryModules : RepositoryModule(){
//
//    @Provides
//    @Singleton
//    fun provideHistoryDatabase() =
//        Mockito.`when`(Mockito.mock(HistoryDatabase::class.java).mainDAO())
//            .thenReturn(FakeHistoryDao())
//
//    @Provides
//    @Singleton
//    fun provideBasketDatabase() =
//        Mockito.`when`(Mockito.mock(BasketDatabase::class.java).mainDAO())
//            .thenReturn(FakeBasketDao())
//
//    @Provides
//    @Singleton
//    fun provideBoughtDatabase() = Mockito.`when`(Mockito.mock(BoughtDatabase::class.java).mainDAO())
//        .thenReturn(FakeProductDao())
//
//    @Provides
//    @Singleton
//    fun provideSaleDatabase() =
//        Mockito.`when`(Mockito.mock(SaleDatabase::class.java).mainDAO())
//            .thenReturn(FakeProductDao())
//
//}

class FakeProductDao(value: List<ProductModel>?) : ProductsDao {

    private val list = value ?: listOf()

    override suspend fun getAll(): List<ProductModel> = list


    override suspend fun delete(model: ProductModel) {
        (list as MutableList).remove(model)
    }

    override suspend fun insert(model: ProductModel) {
        (list as MutableList).add(model)
    }

}

class FakeHistoryDao(value: List<HistoryModel>?) : HistoryDao {
    private val list = value ?: listOf()
    override suspend fun getAll(): List<HistoryModel> = list

    override suspend fun delete(model: HistoryModel) {
        (list as MutableList).remove(model)
    }


    override suspend fun insert(model: HistoryModel) {
        (list as MutableList).add(model)
    }

}

class FakeBasketDao(value: List<BasketModel>?) : BasketDao {

    private val listMain = value ?: listOf()
//
//    fun getList() = listMain

    //    fun getById(id: Int): BasketModel {
//        for (i in listMain) {
//            if (i.id == id) return i
//        }
//        return listMain[0]
//    }
//
//    fun delete(model: BasketModel) = (listMain as MutableList).remove(model)
//
//    fun insert(model: BasketModel) = (listMain as MutableList).add(model)
    override suspend fun getAll(): List<BasketModel> = listMain
    override suspend fun getById(id: Int): BasketModel? {
        for (i in listMain) {
            if (i.id == id) return i
        }
        return null
    }


    override suspend fun delete(model: BasketModel) {
        (listMain as MutableList).remove(model)
    }

    override suspend fun insert(model: BasketModel) {
        (listMain as MutableList).add(model)
    }

}