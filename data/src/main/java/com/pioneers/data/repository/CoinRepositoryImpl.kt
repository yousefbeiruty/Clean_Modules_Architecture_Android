package com.pioneers.data.repository

import com.pioneers.data.api.CoinPaprikaApi
import com.pioneers.data.extensions.toCoin
import com.pioneers.data.extensions.toEvent
import com.pioneers.domain.model.Coin
import com.pioneers.domain.model.EventCoin
import com.pioneers.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi) : CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        return api.getCoins().map { it.toCoin() }
    }

    override suspend fun getEventCoins(): List<EventCoin> {
        return api.getEventCoins().map { it.toEvent() }
    }
}