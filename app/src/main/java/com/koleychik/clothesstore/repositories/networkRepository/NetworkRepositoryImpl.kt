package com.koleychik.clothesstore.repositories.networkRepository

import com.koleychik.clothesstore.models.networkModels.NetworkRequest
import com.koleychik.clothesstore.network.PhotoApi
import retrofit2.Response
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: PhotoApi) : NetworkRepository {

    override suspend fun search(
        optionsString: Map<String, String>,
        optionsInt: Map<String, Int>
    ): Response<NetworkRequest> = api.search(optionsString, optionsInt)
}