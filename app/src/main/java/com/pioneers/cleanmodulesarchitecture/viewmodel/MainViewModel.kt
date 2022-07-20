package com.pioneers.cleanmodulesarchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pioneers.cleanmodulesarchitecture.model.CoinListState
import com.pioneers.domain.common.Resource
import com.pioneers.domain.use_cases.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getCoinUseCase: GetCoinUseCase) : ViewModel() {

    private val _state = MutableLiveData<CoinListState>()
    val state: LiveData<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                  _state.value= CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinListState(coinList = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =  CoinListState(error = it.message ?: "unexpected error accord")
                }

            }
        }.launchIn(viewModelScope)
    }
}