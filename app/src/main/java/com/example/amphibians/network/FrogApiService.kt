package com.example.amphibians.network

import com.example.amphibians.model.Amphibian
import retrofit2.http.GET

interface FrogApiService {
    @GET("amphibians")
    suspend fun getFrogData(): List<Amphibian>
}