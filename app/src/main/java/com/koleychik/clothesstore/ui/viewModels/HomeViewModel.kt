package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.categories.Category
import com.koleychik.clothesstore.repositories.networkRepository.NetworkRepository
import com.koleychik.clothesstore.ui.states.HomeState
import com.koleychik.clothesstore.utils.errorResponse
import com.koleychik.clothesstore.utils.generateProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: NetworkRepository) : ViewModel() {

    val state = MutableLiveData<HomeState>(HomeState.Loading)

    fun getData(
        listCategory: List<Category>
    ) = viewModelScope.launch(Dispatchers.IO)
    {
        val map = mutableMapOf<Int, List<ProductModel>>()

        for (i in listCategory) {
            val optionsString = mapOf("query" to i.getSearchName())
            val optionsInt = mapOf("per_page" to 30)
            val response = repository.search(optionsString, optionsInt)
            if (response.isSuccessful) {
                val networkRequests = response.body()
                if (networkRequests == null) state.value = HomeState.Error(errorResponse(404))
                else map[i.getId()] = generateProductModel(networkRequests.results, i.getId())
            } else {
                state.value = HomeState.Error(errorResponse(response.code()))
                return@launch
            }
        }

        withContext(Dispatchers.Main) {
            Log.d(Constants.TAG, "map.size = ${map.size}")
            state.value = HomeState.Show(listCategory, map)
        }
    }
}
