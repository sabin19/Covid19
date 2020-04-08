package com.sbn.covid19.shared.data.covid

import com.sbn.covid19.shared.db.AppDatabase
import com.sbn.covid19.shared.db.CovidCountry
import com.sbn.covid19.shared.result.Result
import com.sbn.covid19.shared.util.Filter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

interface CovidByCountryRepository {
    fun covidByCountry(filter: Filter): Flow<Result<List<CovidCountry>>>
    fun covidCountry(name: String): Flow<Result<List<CovidCountry>>>
    fun covidCountryByName(name: String): Flow<Result<CovidCountry>>
}

class DefaultCovidByCountryRepository @Inject constructor(private val appDatabase: AppDatabase) :
    CovidByCountryRepository {
    @ExperimentalCoroutinesApi
    override fun covidByCountry(filter: Filter): Flow<Result<List<CovidCountry>>> {
        return when(filter){
                Filter.DEFAULT -> appDatabase.countryDao().covidByCountries().mapLatest { Result.Success(it) }
                Filter.CASE -> appDatabase.countryDao().covidByCountriesCase()
                    .mapLatest { Result.Success(it) }
            Filter.DEATH -> appDatabase.countryDao().covidByCountriesDeaths()
                .mapLatest { Result.Success(it) }
            Filter.RECOVERED -> appDatabase.countryDao().covidByCountriesRecovered()
                .mapLatest { Result.Success(it) }
        }

    }

    @ExperimentalCoroutinesApi
    override fun covidCountry(name: String): Flow<Result<List<CovidCountry>>> =
        appDatabase.countryDao().country(name).mapLatest { Result.Success(it) }

    @ExperimentalCoroutinesApi
    override fun covidCountryByName(name: String): Flow<Result<CovidCountry>> = appDatabase.countryDao().countryByName(name).mapLatest { Result.Success(it) }

}