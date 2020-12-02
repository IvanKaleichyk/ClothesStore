package com.koleychik.clothesstore.di.modules

import com.koleychik.clothesstore.network.PhotoApi
import com.koleychik.clothesstore.network.RetrofitInterceptor
import com.koleychik.clothesstore.repositories.networkRepository.NetworkRepository
import com.koleychik.clothesstore.repositories.networkRepository.NetworkRepositoryImpl
import com.koleychik.clothesstore.utils.constants.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(RetrofitInterceptor())
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(PhotoApi::class.java)

}