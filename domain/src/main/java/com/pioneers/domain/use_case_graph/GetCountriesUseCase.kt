package com.pioneers.domain.use_case_graph

import com.pioneers.domain.repository.CountryRepoSitory
import com.pioneers.domain.model.SimpleCountry
import javax.inject.Inject

class GetCountriesUseCase  constructor(
    private val countryRepository: CountryRepoSitory
) {

    suspend fun execute():List<SimpleCountry>{
        return countryRepository.getCountries().sortedBy { it.name }
    }
}