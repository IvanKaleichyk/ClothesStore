package com.koleychik.clothesstore.di.components

import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import com.koleychik.clothesstore.di.models.*
import com.koleychik.clothesstore.ui.screens.NavDrawerFragment
import com.koleychik.clothesstore.ui.screens.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(navDrawerFragment: NavDrawerFragment)
}