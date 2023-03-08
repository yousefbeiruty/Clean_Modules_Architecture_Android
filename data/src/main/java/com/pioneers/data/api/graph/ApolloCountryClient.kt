package com.pioneers.data.api.graph

import com.apollographql.apollo3.ApolloClient
import com.pioneers.data.model.CountriesQuery
import com.pioneers.data.model.CountryQuery
import com.pioneers.domain.model.DetailCountry
import com.pioneers.domain.model.SimpleCountry
import com.pioneers.domain.repository.CountryRepoSitory
import javax.inject.Inject

class ApolloCountryClient  constructor(
    private val apolloClient: ApolloClient
) : CountryRepoSitory {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}