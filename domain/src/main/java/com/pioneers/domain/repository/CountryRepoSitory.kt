package com.pioneers.domain.repository

import com.pioneers.domain.model.DetailCountry
import com.pioneers.domain.model.SimpleCountry

interface CountryRepoSitory {
    suspend fun getCountries():List<SimpleCountry>
    suspend fun getCountry(code:String):DetailCountry?
}