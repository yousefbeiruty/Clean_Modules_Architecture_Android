package com.pioneers.cleanmodulesarchitecture.view.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pioneers.cleanmodulesarchitecture.model.CoinListState
import com.pioneers.domain.common.Resource
import com.pioneers.domain.model.Coin
import com.pioneers.domain.model.DetailCountry
import com.pioneers.domain.model.SimpleCountry
import com.pioneers.domain.use_case_graph.GetCountriesUseCase
import com.pioneers.domain.use_case_graph.GetCountryUseCase
import com.pioneers.domain.use_cases.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getCoinUseCase: GetCoinUseCase,
private val getCountriesUseCase: GetCountriesUseCase,
private val  getCountryUseCase: GetCountryUseCase) : ViewModel() {


    val listState=MutableStateFlow<CoinListState>(CoinListState())



    private val _state= MutableStateFlow(CountriesState())
    val state=_state.asStateFlow()
    data class CountriesState(
        val countries:List<SimpleCountry> = emptyList(),
        val isLoading:Boolean=false,
        val selectedCountry:DetailCountry?=null
    )

    fun selectCountry(code:String){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = getCountryUseCase.execute(code)
                )
            }
        }
    }

    fun dismissCountryDialog(){
        _state.update {
            it.copy(
                selectedCountry = null
            )
        }
    }
    init {
        getCoins()
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                countries = getCountriesUseCase.execute(),
                isLoading = false
            ) }
        }
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
                    listState.value= CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    listState.value= CoinListState(isLoading = false)
                 //   _state.value = CoinListState(coinList = it.data ?: emptyList())
                    listState.value= CoinListState(coinList = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    listState.value= CoinListState(isLoading = false)
                //    _state.value =  CoinListState(error = it.message ?: "unexpected error accord")
                    listState.value= CoinListState(error = it.message ?: "unexpected error accord")
                }

            }
        }.launchIn(viewModelScope)
    }

}
