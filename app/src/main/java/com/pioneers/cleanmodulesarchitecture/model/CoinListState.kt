package com.pioneers.cleanmodulesarchitecture.model

import com.pioneers.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val error:String=""
)