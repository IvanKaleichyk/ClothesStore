package com.koleychik.clothesstore.repositories

import com.koleychik.clothesstore.network.PhotoApi

class NetworkRepository(private val api : PhotoApi) {

    suspend fun search(
        optionsString: Map<String, String>,
        optionsInt: Map<String, Int>
    ) = api.search(optionsString, optionsInt)
}