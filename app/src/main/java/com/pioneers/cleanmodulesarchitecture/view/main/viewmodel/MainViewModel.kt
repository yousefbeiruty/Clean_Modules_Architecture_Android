package com.pioneers.cleanmodulesarchitecture.view.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.pioneers.cleanmodulesarchitecture.model.ListState
import com.pioneers.domain.common.Resource
import com.pioneers.domain.model.Coin
import com.pioneers.domain.model.EventCoin
import com.pioneers.domain.use_cases.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getCoinUseCase: GetCoinUseCase) : ViewModel() {

//    private val _state = MutableLiveData<CoinListState>()
//    val state: LiveData<CoinListState> = _state

    val listState=MutableStateFlow(ListState<Coin>())

    val listOfEvents=MutableStateFlow(ListState<EventCoin>())

//    val myFlow: Flow<List<Coin>> = flow {
//        getCoinUseCase().onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _state.value= CoinListState(isLoading = true)
//                }
//                is Resource.Success -> {
//                    it.data?.let { it1 -> emit(it1) }
//                    _state.value = CoinListState(coinList = it.data ?: emptyList())
//                }
//                is Resource.Error -> {
//                    _state.value =  CoinListState(error = it.message ?: "unexpected error accord")
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

    init {
        getCoins()
    }

    var selectedItem by mutableStateOf(Coin("",false,"",0,""/*Default Arguments*/)) //Store state as mutableStateOf to ensure proper recompostions
        private set //Do not allow external modification, i.e., outside the ViewModel

    //We'll be modifying the variable through this method
    fun onSelectItem(coin: Coin) {
        selectedItem = coin
    }

    private fun getCoins() {
        getCoinUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                //  _state.value= CoinListState(isLoading = true)
                    listState.value= ListState(isLoading = true)
                }
                is Resource.Success -> {
                    listState.value= ListState(isLoading = false)
                 //   _state.value = CoinListState(coinList = it.data ?: emptyList())
                    listState.value= ListState(data = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    listState.value= ListState(isLoading = false)
                //    _state.value =  CoinListState(error = it.message ?: "unexpected error accord")
                    listState.value= ListState(error = it.message ?: "unexpected error accord")
                }

            }
        }.launchIn(viewModelScope)
    }

     fun getEvents(){
        getCoinUseCase.getEventCoins().onEach {
            when(it){
                is Resource.Loading->{
                    listOfEvents.value= ListState(isLoading = true)
                }
                is Resource.Success->{
                    listOfEvents.value= ListState(isLoading = false)
                    listOfEvents.value= ListState(data = it.data?: emptyList())
                }
                is Resource.Error->{
                    listOfEvents.value= ListState(isLoading = false)
                    listOfEvents.value= ListState(error=it.message ?: "unexpected error accord")
                }
            }
        }.launchIn(viewModelScope)
    }

}


