package com.koleychik.clothesstore.di.models

import androidx.lifecycle.ViewModel
import com.koleychik.clothesstore.ViewModelKey
import com.koleychik.clothesstore.ui.viewModels.MainViewModel
import com.koleychik.clothesstore.ui.viewModels.SearchResultViewModel
import com.koleychik.clothesstore.ui.viewModels.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(viewModel : MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(viewModel : SearchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    abstract fun provideSearchResultViewModel(viewModel : SearchResultViewModel) : ViewModel

}