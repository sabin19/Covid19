
package com.sbn.covid19.shared.di


import com.sbn.covid19.shared.BuildConfig
import com.sbn.covid19.shared.data.NetworkSDataSource
import com.sbn.covid19.shared.data.covid.CovidByCountryRepository
import com.sbn.covid19.shared.data.covid.CovidRepository
import com.sbn.covid19.shared.data.covid.DefaultCovidByCountryRepository
import com.sbn.covid19.shared.data.covid.DefaultCovidRepository
import com.sbn.covid19.shared.db.AppDatabase
import com.sbn.covid19.shared.util.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module where classes created in the shared module are created.
 */
@InstallIn(ApplicationComponent::class)
@Module
class SharedModule {

@Provides
@Singleton
fun provideOkHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.connectTimeout(30, TimeUnit.SECONDS)
    client.readTimeout(30, TimeUnit.SECONDS)
    client.writeTimeout(30, TimeUnit.SECONDS)
    client.retryOnConnectionFailure(false)
    return client.build()
}


    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun providesHttpRequestHandler(
        okHttpClient: OkHttpClient,
        gson: GsonConverterFactory
    ): NetworkSDataSource =
        Retrofit.Builder()
            .addConverterFactory(gson)
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build().create(NetworkSDataSource::class.java)

    @Singleton
    @Provides
    fun provideCountryRepository(
        remoteDataSource: NetworkSDataSource,
        networkUtils: NetworkUtils,
        appDatabase: AppDatabase
    ): CovidRepository {
        return DefaultCovidRepository(remoteDataSource, appDatabase, networkUtils)
    }

    @Singleton
    @Provides
    fun provideCovidByCountryRepository(
        appDatabase: AppDatabase
    ): CovidByCountryRepository {
        return DefaultCovidByCountryRepository(appDatabase)
    }


}
