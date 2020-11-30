package com.koleychik.clothesstore.ui.states

sealed class ImageRvState{

    object Loading : ImageRvState()
    object Nothing : ImageRvState()
    object Show : ImageRvState()
}
