package com.pioneers.domain.repository

import com.pioneers.domain.model.Coin
import com.pioneers.domain.model.EventCoin

interface CoinRepository {
    suspend fun getCoins():List<Coin>

    suspend fun getEventCoins():List<EventCoin>
}