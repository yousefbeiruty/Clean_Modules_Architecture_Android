package com.pioneers.data.extensions

import com.pioneers.data.model.CoinModel
import com.pioneers.domain.model.Coin


fun CoinModel.toCoin(): Coin {
    return Coin(
        id, isActive, name, rank, symbol
    )
}