package com.koleychik.clothesstore.repositories

import com.koleychik.clothesstore.coroutines.CoroutineTestRule
import com.koleychik.clothesstore.data.DataRepository
import com.koleychik.clothesstore.data.getListHistoryModel
import com.koleychik.clothesstore.data.listProductModels
import com.koleychik.clothesstore.database.database.HistoryDatabase
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.models.ProductModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class TestHistoryRepository {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var repository : HistoryRepository

    @Before
    fun setUp(){
        repository = DataRepository.getHistoryRepository()
    }

    @Test
    fun testDelete() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val listInsert = getListHistoryModel()
        val oldList = repository.getAll()
        for (i in listInsert) repository.insert(i)

        val newList = repository.getAll()
        for (i in listInsert) assertThat(newList).contains(i)

        for (i in listInsert) repository.delete(i)
        assertThat(repository.getAll()).isEqualTo(oldList)
    }

    @Test
    fun testInsert() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val listInsert = getListHistoryModel()
        val oldRes = mutableListOf<HistoryModel>()
        oldRes.addAll(repository.getAll())
        for (i in listInsert) repository.insert(i)
        val newList = repository.getAll()

        for (i in listInsert) assertThat(newList).contains(i)
    }

    @Test
    fun testGetAll() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val rightList = getListHistoryModel()
        val listRes = repository.getAll()
        assertThat(listRes.size).isEqualTo(rightList.size)
    }

}