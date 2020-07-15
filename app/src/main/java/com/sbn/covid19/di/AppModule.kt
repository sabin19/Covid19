
package com.sbn.covid19.di

import android.content.Context
import com.sbn.covid19.MainApplication
import com.sbn.covid19.shared.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    fun provideContext(@ApplicationContext application: MainApplication): Context {
        return application.applicationContext
    }


    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.buildDatabase(context)

}
