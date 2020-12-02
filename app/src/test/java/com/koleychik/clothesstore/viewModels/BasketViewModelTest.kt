package com.koleychik.clothesstore.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.koleychik.clothesstore.coroutines.CoroutineTestRule
import com.koleychik.clothesstore.data.DataRepository.Companion.getBasketRepository
import com.koleychik.clothesstore.data.listBasketModels
import com.koleychik.clothesstore.database.dao.BasketDao
import com.koleychik.clothesstore.database.database.BasketDatabase
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.repositories.BasketRepository
import com.koleychik.clothesstore.ui.states.BasketState
import com.koleychik.clothesstore.ui.viewModels.BasketViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.util.*

@ExperimentalCoroutinesApi
class BasketViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: BasketRepository

    private lateinit var viewModel: BasketViewModel

    @Before
    fun setup() {
        repository = getBasketRepository(listBasketModels())
        viewModel = BasketViewModel(repository)
    }

    @Test
    fun `test delete zero`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        viewModel.getAll()
        viewModel.delete(listBasketModels()[0])
        assertThat(repository.getAll()).isEqualTo(repository.getAll())
    }

    @Test
    fun `test delete function`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        viewModel.getAll()
        val deleteModel = repository.getAll()[Random().nextInt(repository.getAll().size)]
        val listRes = mutableListOf<BasketModel>()
        listRes.addAll(repository.getAll())
        listRes.remove(deleteModel)
        viewModel.delete(deleteModel)
        viewModel.getAll()
        val state: BasketState.Show = viewModel.state.value as BasketState.Show
        assertThat(state.list).isEqualTo(listRes)
    }

    @Test
    fun `repository return list`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val stateRes = BasketState.Show(repository.getAll())

        viewModel.getAll()
        assertThat((viewModel.state.value as BasketState.Show).list).isEqualTo(stateRes.list)
    }

    @Test
    fun `repository return empty list`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        repository = getBasketRepository(null)
        viewModel = BasketViewModel(repository)

        val stateRes = BasketState.Nothing

        viewModel.getAll()
        assertThat(viewModel.state.value).isEqualTo(stateRes)
    }

//    private fun whatReturnDao(value: List<BasketModel>?): BasketDatabase {
//
//        val list = value ?: listOf()
//
//        val dao = object : BasketDao {
//            override suspend fun getAll(): List<BasketModel> = list ?: listOf()
//
//            override suspend fun getById(id: Int): BasketModel? {
//                for (i in list) {
//                    if (i.id == id) return i
//                }
//                return null
//            }
//
//            override suspend fun delete(model: BasketModel) {
//                (list as MutableList).remove(model)
//            }
//
//            override suspend fun insert(model: BasketModel) {
//                (list as MutableList).add(model)
//            }
//
//        }
//        val database = Mockito.mock(BasketDatabase::class.java)
//        Mockito.`when`(database.mainDAO()).thenReturn(dao)
//        return database
//    }

}