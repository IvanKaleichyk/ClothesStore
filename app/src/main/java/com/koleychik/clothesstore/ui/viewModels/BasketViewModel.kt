package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.repositories.BasketRepository
import com.koleychik.clothesstore.ui.states.BasketState
import kotlinx.coroutines.launch
import javax.inject.Inject

class BasketViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var repository: BasketRepository

    val state = MutableLiveData<BasketState>(BasketState.Loading)

    fun getAll() = viewModelScope.launch {
        val list = repository.getAll()
        if (list.isEmpty()) state.value = BasketState.Nothing
        else state.value = BasketState.Show(list)
    }

    fun delete(model : BasketModel) = viewModelScope.launch {
        repository.delete(model)
    }

}
