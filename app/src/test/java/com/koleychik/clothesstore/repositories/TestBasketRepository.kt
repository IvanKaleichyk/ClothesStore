package com.koleychik.clothesstore.repositories

import com.koleychik.clothesstore.coroutines.CoroutineTestRule
import com.koleychik.clothesstore.data.DataRepository
import com.koleychik.clothesstore.data.listBasketModels
import com.koleychik.clothesstore.models.BasketModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TestBasketRepository {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var repository: BasketRepository

    @Before
    fun setUp() {
        repository = DataRepository.getBasketRepository(listBasketModels())
    }

    @Test
    fun testDelete() = coroutinesTestRule.testDispatcher.runBlockingTest{
        val list = listBasketModels()
        val oldList = mutableListOf<BasketModel>()
        oldList.addAll(repository.getAll())
        for (i in list) repository.insert(i)
        val newList = repository.getAll()
        for (i in newList) println("newList - ${i.price}")
        for (i in oldList) println("oldList - ${i.price}")
        for (i in list) println("list - ${i.price}")
        assertThat(repository.getAll().size).isEqualTo(list.size + oldList.size)
        for (i in list) repository.delete(i)

        assertThat(repository.getAll()).isEqualTo(oldList)
    }

    @Test
    fun testInsert() = coroutinesTestRule.testDispatcher.runBlockingTest{
        val listRes = listBasketModels()
        for (i in listRes){
            repository.insert(i)
            assertThat(repository.getAll()).contains(i)
        }
    }

    @Test
    fun testGetList() = coroutinesTestRule.testDispatcher.runBlockingTest {

        val listRes = listBasketModels()
        val list = repository.getAll()

        assertThat(list).asList()
        assertThat(list.size).isEqualTo(listRes.size)
    }
}