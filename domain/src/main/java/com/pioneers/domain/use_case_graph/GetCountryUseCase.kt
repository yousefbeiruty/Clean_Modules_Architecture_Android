package com.pioneers.domain.use_case_graph

import com.pioneers.domain.model.DetailCountry
import com.pioneers.domain.repository.CountryRepoSitory
import com.pioneers.domain.model.SimpleCountry
import javax.inject.Inject

class GetCountryUseCase   constructor(
    private val countryRepository: CountryRepoSitory
) {

    suspend fun execute(code:String): DetailCountry?{
        return countryRepository.getCountry(code)
    }
}