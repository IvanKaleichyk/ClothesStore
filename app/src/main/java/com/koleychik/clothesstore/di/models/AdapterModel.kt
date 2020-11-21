package com.koleychik.clothesstore.di.models

import com.koleychik.clothesstore.ui.adapters.CategoryAdapter
import com.koleychik.clothesstore.ui.adapters.HistoryAdapter
import com.koleychik.clothesstore.ui.adapters.ProductAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModel {

    @Provides
    fun provideHistoryAdapter() = HistoryAdapter()

    @Provides
    fun provideProductAdapter() = ProductAdapter()

    @Provides
    fun provideCategoryAdapter() = CategoryAdapter()
}