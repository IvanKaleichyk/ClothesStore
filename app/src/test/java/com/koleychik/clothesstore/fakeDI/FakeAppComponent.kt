package com.koleychik.clothesstore.fakeDI

import androidx.lifecycle.ViewModel
import com.koleychik.clothesstore.di.components.AppComponent
import com.koleychik.clothesstore.di.modules.RepositoryModule
import com.koleychik.clothesstore.repositories.TestBasketRepository
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

//@Component(modules = [RepositoryModule::class])
//@Singleton
//interface FakeAppComponent : AppComponent{
//
////    fun viewModelFactory(viewModel : ViewModel) = MainViewModelFactory(viewModels = viewModel)
//
//    @ExperimentalCoroutinesApi
//    fun inject(test: TestBasketRepository)
//
//}