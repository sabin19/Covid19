
package com.sbn.covid19.di

import com.sbn.covid19.shared.di.DefaultDispatcher
import com.sbn.covid19.shared.di.IoDispatcher
import com.sbn.covid19.shared.di.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(ApplicationComponent::class)
@Module
object CoroutinesModule {

    @DefaultDispatcher
    @JvmStatic
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @JvmStatic
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @JvmStatic
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
