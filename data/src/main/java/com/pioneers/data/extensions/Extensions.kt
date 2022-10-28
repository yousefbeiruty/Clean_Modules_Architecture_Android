package com.pioneers.data.extensions

import com.pioneers.data.model.CoinModel
import com.pioneers.data.model.EventModel
import com.pioneers.data.model.EventModelItem
import com.pioneers.domain.model.Coin
import com.pioneers.domain.model.EventCoin


fun CoinModel.toCoin(): Coin {
    return Coin(
        id, isActive, name, rank, symbol
    )
}

fun EventModelItem.toEvent(): EventCoin {
   return EventCoin( date,
     date_to,
     description,
     id,
     is_conference,
     link,
     name,
     proof_image_link)
}