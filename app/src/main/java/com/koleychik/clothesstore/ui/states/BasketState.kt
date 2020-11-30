package com.koleychik.clothesstore.ui.states

import com.koleychik.clothesstore.models.BasketModel

sealed class BasketState {
    object Loading : BasketState()
    object Nothing: BasketState()
    class Show(val list: List<BasketModel>): BasketState()
}
