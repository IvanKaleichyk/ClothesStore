package com.koleychik.clothesstore.ui.states

import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.utils.constants.Constants

sealed class SearchResultState {

    class Loading(val searchWord: String, val page : Int, val per_page: Int = Constants.per_page) : SearchResultState()
    class Error(val errorText: String) : SearchResultState()
    object Nothing : SearchResultState()
    object Showing : SearchResultState()
}