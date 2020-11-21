package com.koleychik.clothesstore.network

import com.koleychik.clothesstore.models.networkModels.NetworkRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PhotoApi {

    @GET("/search/photos")
    suspend fun search(
        @QueryMap optionsString : Map<String, String>,
        @QueryMap optionsInt: Map<String, Int>
        ) : Response<NetworkRequest>
}