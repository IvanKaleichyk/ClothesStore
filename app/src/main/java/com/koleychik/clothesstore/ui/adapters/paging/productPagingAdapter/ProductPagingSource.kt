package com.koleychik.clothesstore.ui.adapters.paging.productPagingAdapter

import androidx.paging.PagingSource
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.repositories.NetworkRepository
import com.koleychik.clothesstore.utils.generateProductModel
import java.lang.Exception

class ProductPagingSource(
    private val repository: NetworkRepository,
    private val categoryId: Int,
    private val optionsString: Map<String, String>,
    private val per_page: Int,
    private val priceMin: Int,
    private val priceMax: Int
) : PagingSource<Int, ProductModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductModel> {
        return try {
            val page = params.key ?: 0
            val requests = repository.search(
                optionsString,
                mapOf("per_page" to per_page, "page" to page)
            )

            LoadResult.Page(
                data = generateProductModel(requests.body()!!.results, categoryId, priceMin, priceMax),
                prevKey = if (page > 0) page - 1 else null,
                nextKey = if (requests.body()!!.results.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}