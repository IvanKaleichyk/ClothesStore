package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.categories.Category
import com.koleychik.clothesstore.repositories.NetworkRepository
import com.koleychik.clothesstore.ui.states.SearchResultState
import com.koleychik.clothesstore.utils.errorResponse
import com.koleychik.clothesstore.utils.generateProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultViewModel @Inject constructor() : ViewModel() {

    val totalPages = MutableLiveData(10)

    @Inject
    lateinit var repository: NetworkRepository

    val state = MutableLiveData<SearchResultState>(null)

    val pageNow = MutableLiveData(1)

    fun search(
        category: Category,
        optionsString: Map<String, String>,
        optionsInt: Map<String, Int>
    ) =
        viewModelScope.launch {
            val response = repository.search(optionsString, optionsInt)
            if (response.isSuccessful) {
                val list = response.body()
                if (list == null || list.results.isEmpty()) {
                    if (totalPages.value == 10 || totalPages.value == null) totalPages.value = list!!.total_pages
                    SearchResultState.Showing(generateProductModel(
                            list!!.results,
                            category = category))
                } else state.value = SearchResultState.Nothing
            } else {
                state.value = SearchResultState.Error(errorResponse(response.code()))
            }
        }


}