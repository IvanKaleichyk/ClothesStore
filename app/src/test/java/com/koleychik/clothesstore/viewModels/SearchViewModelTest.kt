package com.koleychik.clothesstore.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.koleychik.clothesstore.coroutines.CoroutineTestRule
import com.koleychik.clothesstore.data.DataRepository.Companion.getHistoryRepository
import com.koleychik.clothesstore.data.getListHistoryModel
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.repositories.HistoryRepository
import com.koleychik.clothesstore.ui.states.SearchState
import com.koleychik.clothesstore.ui.viewModels.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: HistoryRepository
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        repository = getHistoryRepository(getListHistoryModel())
        viewModel = SearchViewModel(repository)
    }

    @Test
    fun `test delete`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        val oldList = mutableListOf<HistoryModel>()
        oldList.addAll(repository.getAll())
        val deleteModel = repository.getAll()[Random().nextInt(oldList.size)]
        viewModel.delete(deleteModel)
        assertThat(repository.getAll()).isNotEqualTo(oldList)
        oldList.remove(deleteModel)
        assertThat(repository.getAll()).isEqualTo(oldList)
    }

    @Test
    fun `test insert`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val oldList = mutableListOf<HistoryModel>()
        val insertModel = getListHistoryModel()[0]
        oldList.addAll(repository.getAll())
        viewModel.insert(insertModel)
        assertThat(repository.getAll()).isNotEqualTo(oldList)
        oldList.add(insertModel)
        assertThat(repository.getAll()).isEqualTo(oldList)
    }

    @Test
    fun `test repository return list`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val listRes = repository.getAll()
        viewModel.getList()
        assertThat((viewModel.state.value as SearchState.ShowResult).list).isEqualTo(listRes)
    }

    @Test
    fun `test repository return empty list`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        repository = getHistoryRepository(mutableListOf())
        viewModel = SearchViewModel(repository)

        viewModel.getList()
        assertThat(viewModel.state.value).isEqualTo(SearchState.Loading)
    }

}