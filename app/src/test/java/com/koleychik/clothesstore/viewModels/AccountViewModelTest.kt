package com.koleychik.clothesstore.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.koleychik.clothesstore.coroutines.CoroutineTestRule
import com.koleychik.clothesstore.data.DataRepository.Companion.getDeviceImagesRepository
import com.koleychik.clothesstore.data.getListDeviceImages
import com.koleychik.clothesstore.models.DeviceImage
import com.koleychik.clothesstore.repositories.DeviceImagesRepository
import com.koleychik.clothesstore.ui.viewModels.AccountViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AccountViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: DeviceImagesRepository

    private lateinit var list: List<DeviceImage>

    private lateinit var viewModel: AccountViewModel

    @Before
    fun setUp() {
        list = getListDeviceImages()
        repository = getDeviceImagesRepository(list as MutableList)
        viewModel = AccountViewModel(repository)
    }

    @Test
    fun `then repository return list`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        viewModel.getAllImages()
        assertThat(viewModel.listImages.value).isEqualTo(list)
    }

    @Test
    fun `then repository return null`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        repository = getDeviceImagesRepository(null)
        viewModel = AccountViewModel(repository)

        viewModel.getAllImages()
        assertThat(viewModel.listImages.value).isEqualTo(listOf<DeviceImage>())
    }

    @Test
    fun `then repository return emptyList`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        repository = getDeviceImagesRepository(mutableListOf())
        viewModel = AccountViewModel(repository)

        viewModel.getAllImages()
        assertThat(viewModel.listImages.value).isEqualTo(listOf<DeviceImage>())
    }

}