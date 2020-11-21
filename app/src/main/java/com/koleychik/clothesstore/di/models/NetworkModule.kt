package com.koleychik.clothesstore.di.models

import com.koleychik.clothesstore.network.PhotoApi
import com.koleychik.clothesstore.network.RetrofitInterceptor
import com.koleychik.clothesstore.repositories.NetworkRepository
import com.koleychik.clothesstore.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(api: PhotoApi) = NetworkRepository(api)

    @Provides
    fun provideClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(RetrofitInterceptor())
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(PhotoApi::class.java)

}