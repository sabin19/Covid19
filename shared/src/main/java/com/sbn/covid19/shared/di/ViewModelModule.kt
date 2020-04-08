
package com.sbn.covid19.shared.di

import androidx.lifecycle.ViewModelProvider
import com.codeatech.grocery.shared.di.CovidViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Module used to define the connection between the framework's [ViewModelProvider.Factory] and
 * our own implementation: [CovidViewModelFactory].
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: CovidViewModelFactory):
        ViewModelProvider.Factory
}
