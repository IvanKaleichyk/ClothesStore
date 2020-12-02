package com.koleychik.clothesstore.repositories.networkRepository

import com.koleychik.clothesstore.models.networkModels.NetworkRequest
import retrofit2.Response

interface NetworkRepository {

    suspend fun search(
        optionsString: Map<String, String>,
        optionsInt: Map<String, Int>
    ): Response<NetworkRequest>
}