package com.koleychik.clothesstore.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.categories.Category
import com.koleychik.clothesstore.repositories.NetworkRepository
import com.koleychik.clothesstore.ui.states.HomeState
import com.koleychik.clothesstore.utils.constants.Constants
import com.koleychik.clothesstore.utils.errorResponse
import com.koleychik.clothesstore.utils.generateProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    val state = MutableLiveData<HomeState>(HomeState.Loading)
    @Inject
    lateinit var repository: NetworkRepository

    fun getData(listCategory: List<Category>) = viewModelScope.launch {
        Log.d(Constants.TAG, "startGetData")
        val map = mutableMapOf<Int, List<ProductModel>>()

        for (i in listCategory){
            val optionsString = mapOf("query" to i.getSearchName())
            val optionsInt = mapOf("per_page" to 20)
            val response = repository.search(optionsString, optionsInt)
            if (response.isSuccessful){
                val networkRequests = response.body()
                if (networkRequests == null) state.value = HomeState.Error(errorResponse(404))
                else map[i.getId()] = generateProductModel(networkRequests.results, i.getId())
            }
            else {
                state.value = HomeState.Error(errorResponse(response.code()))
                return@launch
            }
        }

        Log.d(Constants.TAG, "map.size = ${map.size}")
        state.value = HomeState.Show(listCategory, map)
    }
}
