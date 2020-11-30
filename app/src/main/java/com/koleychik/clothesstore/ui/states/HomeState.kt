package com.koleychik.clothesstore.ui.states

import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.categories.Category

sealed class HomeState {
    object Loading : HomeState()
    object Refreshing : HomeState()
    class Error(val textRes: Int) : HomeState()
    class Show(val lisCategory: List<Category>, val mapListProducts: Map<Int, List<ProductModel>>) :
        HomeState()
}
