package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.repositories.HistoryRepository
import com.koleychik.clothesstore.ui.states.SearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: HistoryRepository) : ViewModel() {

    val state = MutableLiveData<SearchState>(SearchState.Loading)

    val valueSpinnerCategory = MutableLiveData(0)

    fun getList(stateIfListEmpty : SearchState? = null) = viewModelScope.launch {
        val list = repository.getAll()
        withContext(Dispatchers.Main){
            if (list.isNotEmpty()) state.value = SearchState.ShowResult(list)
            else if (stateIfListEmpty != null) state.value = stateIfListEmpty
        }
    }

    fun insert(model: HistoryModel) = viewModelScope.launch{
        repository.insert(model)
    }

    fun delete(model: HistoryModel) = viewModelScope.launch{
        repository.delete(model)
    }
}