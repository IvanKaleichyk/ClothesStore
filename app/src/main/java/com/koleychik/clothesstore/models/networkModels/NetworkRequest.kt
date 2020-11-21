package com.koleychik.clothesstore.models.networkModels

data class NetworkRequest(
    val results: List<Photo>,
    val total: Int,
    val total_pages: Int
)