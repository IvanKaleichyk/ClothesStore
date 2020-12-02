package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.repositories.BasketRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val basketRepository : BasketRepository) : ViewModel(){

    val isInBasket = MutableLiveData<Boolean>()
    val isInFavorites = MutableLiveData<Boolean>()

    val selectSize = MutableLiveData<Int>()

    fun insertInBasket(model: BasketModel) = viewModelScope.launch {
        basketRepository.insert(model)
    }

    suspend fun checkValueInDb(id : Int): Boolean = basketRepository.getById(id) != null


}
