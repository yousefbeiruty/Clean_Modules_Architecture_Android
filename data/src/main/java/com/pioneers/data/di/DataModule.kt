package com.pioneers.data.di

import com.apollographql.apollo3.ApolloClient
import com.pioneers.data.api.CoinPaprikaApi
import com.pioneers.data.api.graph.ApolloCountryClient
import com.pioneers.data.repository.CoinRepositoryImpl
import com.pioneers.domain.common.Constants.BASE_URL
import com.pioneers.domain.repository.CoinRepository
import com.pioneers.domain.repository.CountryRepoSitory
import com.pioneers.domain.use_case_graph.GetCountriesUseCase
import com.pioneers.domain.use_case_graph.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }
    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryRepoSitory {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countryClient: CountryRepoSitory): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun provideGetCountryUseCase(countryClient: CountryRepoSitory): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }
}