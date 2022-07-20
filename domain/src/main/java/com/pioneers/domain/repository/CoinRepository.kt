package com.pioneers.domain.repository

import com.pioneers.domain.model.Coin

interface CoinRepository {
    suspend fun getCoins():List<Coin>
}