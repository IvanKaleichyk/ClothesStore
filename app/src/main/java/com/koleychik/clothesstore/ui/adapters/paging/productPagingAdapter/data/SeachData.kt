package com.koleychik.clothesstore.ui.adapters.paging.productPagingAdapter.data

data class SearchData(
    val categoryId: Int,
    val optionsString: Map<String, String>,
    val optionsInt: Map<String, Int>)