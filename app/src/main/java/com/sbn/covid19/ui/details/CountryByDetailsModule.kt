
package com.sbn.covid19.ui.details

import androidx.lifecycle.ViewModel
import com.sbn.covid19.shared.di.FragmentScoped
import com.sbn.covid19.shared.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Module where classes needed to create the [CountryByDetailFragment] are defined.
 */
@Module
@Suppress("UNUSED")
internal abstract class CountryByDetailsModule {

    /**
     * Generates an [AndroidInjector] for the [CountryByDetailFragment]
     */
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeCountryByDetailFragment(): CountryByDetailFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [CountryByDetailsViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(CountryByDetailsViewModel::class)
    internal abstract fun bindCountryByDetailsViewModel(viewModel: CountryByDetailsViewModel): ViewModel
}