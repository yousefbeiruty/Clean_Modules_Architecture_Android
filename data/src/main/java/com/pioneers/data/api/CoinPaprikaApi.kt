package com.pioneers.data.api

import com.pioneers.data.model.CoinModel
import com.pioneers.data.model.EventModelItem
import retrofit2.http.GET

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinModel>

    @GET("v1/coins/btc-bitcoin/events")
    suspend fun getEventCoins():List<EventModelItem>
}