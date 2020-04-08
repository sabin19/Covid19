package com.sbn.covid19.shared.data.covid

import com.sbn.covid19.shared.data.NetworkSDataSource
import com.sbn.covid19.shared.db.AppDatabase
import com.sbn.covid19.shared.db.CovidCountry
import com.sbn.covid.model.GlobalCovid
import com.sbn.covid19.shared.data.RemoteRepository
import com.sbn.covid19.shared.result.Result
import com.sbn.covid19.shared.util.NetworkUtils
import javax.inject.Inject

interface CovidRepository {
    suspend fun globalCovid(): Result<GlobalCovid>
    suspend fun countryCovid(): Result<Boolean>
}

class DefaultCovidRepository @Inject constructor(
    private val dataSource: NetworkSDataSource,
    private val appDatabase: AppDatabase,
    networkUtils: NetworkUtils
) : RemoteRepository(networkUtils),
    CovidRepository {
    override suspend fun globalCovid(): Result<GlobalCovid> {
        return when (val result = execute { dataSource.globalCovid() }) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Error -> {
                Result.Error(result.exception)
            }
            else -> Result.Error(Exception(""))
        }
    }


    override suspend fun countryCovid(): Result<Boolean> {
        return when (val result = execute { dataSource.countryCovid() }) {
            is Result.Success -> {
                appDatabase.countryDao().insert(result.data)
                Result.Success(true)
            }
            is Result.Error -> {
                Result.Error(result.exception)
            }
            else -> Result.Error(Exception(""))
        }

    }
}