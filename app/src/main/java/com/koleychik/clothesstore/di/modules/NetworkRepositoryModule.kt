package com.koleychik.clothesstore.di.modules

import com.koleychik.clothesstore.repositories.networkRepository.NetworkRepository
import com.koleychik.clothesstore.repositories.networkRepository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NetworkRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository
}