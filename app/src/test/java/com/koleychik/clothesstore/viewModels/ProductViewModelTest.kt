package com.koleychik.clothesstore.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.koleychik.clothesstore.coroutines.CoroutineTestRule
import com.koleychik.clothesstore.data.DataRepository.Companion.getBasketRepository
import com.koleychik.clothesstore.data.listBasketModels
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.repositories.BasketRepository
import com.koleychik.clothesstore.ui.viewModels.ProductViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: BasketRepository
    private lateinit var viewModel: ProductViewModel

    @Before
    fun setup() {
        repository = getBasketRepository(listBasketModels())
        viewModel = ProductViewModel(repository)
    }

    @Test
    fun `test if value in db return false`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val model = viewModel.checkValueInDb(1937972)
        assertThat(model).isFalse()
    }

    @Test
    fun `test if value in db return true`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val model = viewModel.checkValueInDb(repository.getAll()[0].id)
        assertThat(model).isTrue()
    }

    @Test
    fun `test insert`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val oldList = mutableListOf<BasketModel>()
        oldList.addAll(repository.getAll())
        val listBasketModels = listBasketModels()
        viewModel.insertInBasket(listBasketModels[0])
        assertThat(repository.getAll()).isNotEqualTo(oldList)
    }

}