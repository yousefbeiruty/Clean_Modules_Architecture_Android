package com.pioneers.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data  class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
): Parcelable