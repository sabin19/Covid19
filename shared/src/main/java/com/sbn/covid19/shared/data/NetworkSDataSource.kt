package com.sbn.covid19.shared.data




import com.sbn.covid.model.GlobalCovid
import com.sbn.covid19.shared.db.CovidCountry
import retrofit2.Response
import retrofit2.http.*

interface NetworkSDataSource {

    @GET("all")
    suspend fun globalCovid(): Response<GlobalCovid>

    @GET("countries")
    suspend fun countryCovid(): Response<List<CovidCountry>>


}