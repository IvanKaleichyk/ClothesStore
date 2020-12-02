package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.repositories.HistoryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private var historyRepository: HistoryRepository) :
    ViewModel() {

    val textSearch = MutableLiveData<String>()

    fun insert(model: HistoryModel) = viewModelScope.launch {
        historyRepository.insert(model)
    }


}