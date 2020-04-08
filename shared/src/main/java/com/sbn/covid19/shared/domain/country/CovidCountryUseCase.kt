package com.sbn.covid19.shared.domain.country

import com.sbn.covid19.shared.data.covid.CovidByCountryRepository
import com.sbn.covid19.shared.db.CovidCountry
import com.sbn.covid19.shared.domain.FlowUseCase
import com.sbn.covid19.shared.di.IoDispatcher
import com.sbn.covid19.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.sbn.covid19.shared.util.Filter

class CovidCountryUseCase @Inject constructor(
    private val repository: CovidByCountryRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Pair<String?,Filter>, List<CovidCountry>>(ioDispatcher) {
    @ExperimentalCoroutinesApi
    override fun execute(parameters: Pair<String?,Filter>): Flow<Result<List<CovidCountry>>> {
        return flow {
            emitAll(if(parameters.first.isNullOrEmpty()) repository.covidByCountry(parameters.second) else repository.covidCountry(parameters.first!!))
        }
    }
}