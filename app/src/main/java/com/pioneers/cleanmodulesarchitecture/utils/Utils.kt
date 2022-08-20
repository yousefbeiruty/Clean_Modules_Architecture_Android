package com.pioneers.cleanmodulesarchitecture.utils

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import com.pioneers.domain.model.Coin

class AssetParamType : NavType<Coin>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Coin? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Coin {
        return Gson().fromJson(value, Coin::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Coin) {
        bundle.putParcelable(key, value)
    }
}