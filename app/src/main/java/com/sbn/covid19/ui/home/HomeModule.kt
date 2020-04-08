
package com.sbn.covid19.ui.home

import androidx.lifecycle.ViewModel
import com.sbn.covid19.shared.di.FragmentScoped
import com.sbn.covid19.shared.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Module where classes needed to create the [HomeFragment] are defined.
 */
@Module
@Suppress("UNUSED")
internal abstract class HomeModule {

    /**
     * Generates an [AndroidInjector] for the [HomeFragment]
     */
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [HomeViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}