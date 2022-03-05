package com.sbn.covid19.shared.domain.covid

import com.sbn.covid19.shared.data.covid.CovidByCountryRepository
import com.sbn.covid19.shared.db.CovidCountry
import com.sbn.covid19.shared.di.IoDispatcher
import com.sbn.covid19.shared.domain.FlowUseCase
import com.sbn.covid19.shared.result.Result
import com.sbn.covid19.shared.util.Filter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryDetailsUseCase  @Inject constructor(
    private val repository: CovidByCountryRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<String, CovidCountry>(ioDispatcher) {
    override fun execute(parameters: String): Flow<Result<CovidCountry>> {
        return flow {
            emitAll(repository.covidCountryByName(parameters))
        }
    }
}