package com.koleychik.clothesstore.di.modules

import androidx.lifecycle.ViewModel
import com.koleychik.clothesstore.ViewModelKey
import com.koleychik.clothesstore.ui.viewModels.*
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

    @Binds
    @IntoMap
    @ViewModelKey(BasketViewModel::class)
    abstract fun provideBasketViewModel(viewModel : BasketViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun provideProductViewModel(viewModel : ProductViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(viewModel : HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignViewModel::class)
    abstract fun provideSignViewModel(viewModel : SignViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ElementLoginViewModel::class)
    abstract fun provideElementLoginViewModel(viewModel : ElementLoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun provideAccountViewModel(viewModel : AccountViewModel) : ViewModel
}