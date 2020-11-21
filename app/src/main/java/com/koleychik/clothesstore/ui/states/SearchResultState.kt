package com.koleychik.clothesstore.ui.states

import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.utils.Constants

sealed class SearchResultState {

    class Loading(val searchWord: String, val page : Int, val per_page: Int = Constants.per_page) : SearchResultState()
    class Error(val errorTextResources: Int) : SearchResultState()
    object Nothing : SearchResultState()
    class Showing(val list: List<ProductModel>) : SearchResultState()
}