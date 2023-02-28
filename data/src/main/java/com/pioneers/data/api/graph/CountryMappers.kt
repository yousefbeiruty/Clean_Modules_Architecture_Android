package com.pioneers.data.api.graph

import com.pioneers.data.model.CountriesQuery
import com.pioneers.data.model.CountryQuery
import com.pioneers.domain.model.DetailCountry
import com.pioneers.domain.model.SimpleCountry

fun CountryQuery.Country.toDetailedCountry():DetailCountry{
   return DetailCountry(
       code=code,
       name=name,
       emoji=emoji,
       capital=capital ?: "No capital",
       currency=currency ?: "No currency",
       languages = languages.mapNotNull { it.name },
       continent=continent.name
   )
}

fun CountriesQuery.Country.toSimpleCountry():SimpleCountry{
    return SimpleCountry(
        code=code,
        name=name,
        emoji=emoji,
        capital=capital ?: "No capital",
    )
}