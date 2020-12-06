package com.koleychik.clothesstore.di.modules

import com.koleychik.clothesstore.ui.adapters.CategoryAdapter
import com.koleychik.clothesstore.ui.adapters.HistoryAdapter
import com.koleychik.clothesstore.ui.adapters.ImagesAdapter
import com.koleychik.clothesstore.ui.adapters.ProductAdapter
import com.koleychik.clothesstore.ui.adapters.paging.productPagingAdapter.ProductPagingAdapter
import com.koleychik.clothesstore.utils.ActiveModel
import dagger.Module
import dagger.Provides

@Module
class AdapterModel {

    @Provides
    fun provideHistoryAdapter() = HistoryAdapter()

//    @Provides
//    fun provideProductAdapter(activeModel: ActiveModel) = ProductAdapter(activeModel)
//
//    @Provides
//    fun provideCategoryAdapter(activeModel: ActiveModel) = CategoryAdapter(activeModel)

    @Provides
    fun provideAdapterProducts(activeModel: ActiveModel) = ProductPagingAdapter(activeModel)

    @Provides
    fun provideImagesAdapter() = ImagesAdapter()
}