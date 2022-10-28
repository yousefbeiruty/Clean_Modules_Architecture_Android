package com.pioneers.domain.use_cases.get_coins

import com.pioneers.domain.common.Resource
import com.pioneers.domain.model.Coin
import com.pioneers.domain.model.EventCoin
import com.pioneers.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins()
            emit(Resource.Success(coins))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection!"))
        }
    }

    fun getEventCoins():Flow<Resource<List<EventCoin>>> = flow{
       try {
           emit(Resource.Loading())
           val events = repository.getEventCoins()
           emit(Resource.Success(events))
       }catch (e:IOException){
           emit(Resource.Error(message = "Couldn't reach server. Check your internet connection!"))
       }
    }
}