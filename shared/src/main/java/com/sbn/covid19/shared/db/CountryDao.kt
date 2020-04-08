package com.sbn.covid19.shared.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sbn.covid19.shared.db.CovidCountry
import com.sbn.covid19.shared.util.Filter
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<CovidCountry>)

    @Query("select * from country order by cases  desc")
    fun covidByCountries(): Flow<List<CovidCountry>>

    @Query("select * from country order by cases  desc")
    fun covidByCountriesCase(): Flow<List<CovidCountry>>

    @Query("select * from country order by deaths  desc")
    fun covidByCountriesDeaths(): Flow<List<CovidCountry>>

    @Query("select * from country order by recovered  desc")
    fun covidByCountriesRecovered(): Flow<List<CovidCountry>>

    @Query("select * from country where lower(country) like   lower(:name) || '%'")
    fun country(name: String): Flow<List<CovidCountry>>

    @Query("select * from country where country = :name")
    fun countryByName(name: String): Flow<CovidCountry>

}