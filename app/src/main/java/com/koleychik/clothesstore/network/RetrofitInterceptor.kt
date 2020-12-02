package com.koleychik.clothesstore.network

import com.koleychik.clothesstore.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RetrofitInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("client_id", BuildConfig.ACCESS_KEY)
            .build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}