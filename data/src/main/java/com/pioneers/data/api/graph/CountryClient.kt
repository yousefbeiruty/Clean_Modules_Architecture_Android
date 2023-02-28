package com.pioneers.data.api.graph

import com.pioneers.data.model.CountryQuery
import com.pioneers.domain.model.DetailCountry
import com.pioneers.domain.model.SimpleCountry

interface CountryClient {
    suspend fun getCountries():List<SimpleCountry>
    suspend fun getCountry(code:String):DetailCountry?
}