package com.example.amphibians.data

import com.example.amphibians.network.FrogApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer{
    val frogRepo: FrogRepo
}
class DefaultAppContainer: AppContainer{
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"
    // retro fit build
    @kotlinx.serialization.ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
    @OptIn(ExperimentalSerializationApi::class)
    private val retrofitService: FrogApiService by lazy {
        retrofit.create(FrogApiService::class.java)
    }
    override val frogRepo: FrogRepo by lazy{
        FrogNetworkData(retrofitService)
    }

}