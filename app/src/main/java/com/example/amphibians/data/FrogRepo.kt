package com.example.amphibians.data

import com.example.amphibians.model.Amphibian
import com.example.amphibians.network.FrogApiService

interface FrogRepo{
    suspend fun getFrogData(): List<Amphibian>
}
class FrogNetworkData(
    private val frogAPIService: FrogApiService
    ) : FrogRepo{
    override suspend fun getFrogData(): List<Amphibian> = frogAPIService.getFrogData()

    }
