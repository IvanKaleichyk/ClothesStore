package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.koleychik.clothesstore.repositories.NetworkRepository
import com.koleychik.clothesstore.ui.adapters.paging.productPagingAdapter.ProductPagingSource
import com.koleychik.clothesstore.ui.states.SearchResultState
import javax.inject.Inject

class SearchResultViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var repository: NetworkRepository

    val state = MutableLiveData<SearchResultState>(null)

    val pageNow = MutableLiveData(1)

    fun products(
        categoryId: Int,
        per_pager: Int,
        optionsString: Map<String, String>,
        priceMin : Int,
        priceMax: Int
    ) = Pager(PagingConfig(per_pager)) {
        ProductPagingSource(
            repository,
            categoryId,
            optionsString,
            per_pager,
            priceMin,
            priceMax
        )
    }.flow
}