package com.sbn.covid19.shared.domain.covid

import com.sbn.covid.model.GlobalCovid
import com.sbn.covid19.shared.data.covid.CovidByCountryRepository
import com.sbn.covid19.shared.data.covid.CovidRepository
import com.sbn.covid19.shared.db.CovidCountry
import com.sbn.covid19.shared.di.IoDispatcher
import com.sbn.covid19.shared.domain.FlowUseCase
import com.sbn.covid19.shared.domain.SuspendUseCase
import com.sbn.covid19.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import java.lang.IllegalStateException
import javax.inject.Inject

class CovidUseCase @Inject constructor(
    private val repository: CovidRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Unit, GlobalCovid>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): GlobalCovid {
        return when(val res = repository.globalCovid()){
            is Result.Success -> res.data
            is Result.Error -> throw res.exception
            is Result.Loading -> throw IllegalStateException("Illegal state exception")
        }
    }

}