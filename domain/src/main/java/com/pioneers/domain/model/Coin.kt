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

@Parcelize
data class EventCoin(
    val date: String,
    val date_to: String,
    val description: String,
    val id: String,
    val is_conference: Boolean,
    val link: String,
    val name: String,
    val proof_image_link: String
) : Parcelable