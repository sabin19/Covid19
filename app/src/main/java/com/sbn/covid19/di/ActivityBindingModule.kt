package com.sbn.covid19.di

import com.sbn.covid19.MainActivity
import com.sbn.covid19.MainActivityModule
import com.sbn.covid19.shared.di.ActivityScoped
import com.sbn.covid19.ui.countries.CountryModule
import com.sbn.covid19.ui.details.CountryByDetailsModule
import com.sbn.covid19.ui.home.HomeModule
import com.sbn.covid19.ui.info.InfoModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {


    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            MainActivityModule::class,
            // fragments
            HomeModule::class,
            CountryModule::class,
            InfoModule::class,
            CountryByDetailsModule::class

        ]
    )
    internal abstract fun mainActivity(): MainActivity


}
