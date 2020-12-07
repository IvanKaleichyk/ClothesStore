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
//    fun provideProductAdapter() = ProductAdapter()
//
//    @Provides
//    fun provideCategoryAdapter() = CategoryAdapter()
//
//    @Provides
//    fun provideAdapterProducts() = ProductPagingAdapter()

    @Provides
    fun provideImagesAdapter() = ImagesAdapter()
}