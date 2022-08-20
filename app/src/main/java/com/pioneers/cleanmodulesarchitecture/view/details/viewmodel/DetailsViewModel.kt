package com.pioneers.cleanmodulesarchitecture.view.details.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pioneers.domain.model.Coin
import com.pioneers.domain.use_cases.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel@Inject constructor(private val getCoinUseCase: GetCoinUseCase) :ViewModel() {

/*This variable shall store the photo,
Which I plan to use as an ID for the Profile Card.*/


}