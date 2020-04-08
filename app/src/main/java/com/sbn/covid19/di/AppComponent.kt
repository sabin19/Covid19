
package com.sbn.covid19.di

import com.sbn.covid19.MainApplication
import com.sbn.covid19.shared.di.SharedModule
import com.sbn.covid19.shared.di.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Main component of the app, created and persisted in the Application class.
 *
 * Whenever a new module is created, it should be added to the list of modules.
 * [AndroidSupportInjectionModule] is the module from Dagger.Android that helps with the
 * generation and location of subcomponents.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        CoroutinesModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class,
        SharedModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<MainApplication>
}
