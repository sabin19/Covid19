
package com.sbn.covid19.ui.countries

import androidx.lifecycle.ViewModel
import com.sbn.covid19.shared.di.FragmentScoped
import com.sbn.covid19.shared.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Module where classes needed to create the [CountryFragment] are defined.
 */
@Module
@Suppress("UNUSED")
internal abstract class CountryModule {

    /**
     * Generates an [AndroidInjector] for the [CountryFragment]
     */
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeCountryFragment(): CountryFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [CountryViewModel] class.
     */
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(CountryViewModel::class)
    internal abstract fun bindCountryViewModel(viewModel: CountryViewModel): ViewModel
}