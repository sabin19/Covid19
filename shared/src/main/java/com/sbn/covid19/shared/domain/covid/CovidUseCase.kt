package com.sbn.covid19.shared.domain.covid

import com.sbn.covid.model.GlobalCovid
import com.sbn.covid19.shared.data.covid.CovidRepository
import com.sbn.covid19.shared.di.IoDispatcher
import com.sbn.covid19.shared.domain.UseCase
import com.sbn.covid19.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CovidUseCase @Inject constructor(
    private val repository: CovidRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, GlobalCovid>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): GlobalCovid {
        return when(val res = repository.globalCovid()){
            is Result.Success -> res.data
            is Result.Error -> throw res.exception
            is Result.Loading -> throw IllegalStateException("Illegal state exception")
        }
    }

}