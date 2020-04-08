
package com.sbn.covid19.ui.info

import androidx.lifecycle.ViewModel
import com.sbn.covid19.shared.di.FragmentScoped
import com.sbn.covid19.shared.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Module where classes needed to create the [InfoFragment] are defined.
 */
@Module
@Suppress("UNUSED")
internal abstract class InfoModule {

    /**
     * Generates an [AndroidInjector] for the [InfoFragment]
     */
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeInfoFragment(): InfoFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [InfoViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(InfoViewModel::class)
    internal abstract fun bindInfoViewModel(viewModel: InfoViewModel): ViewModel
}