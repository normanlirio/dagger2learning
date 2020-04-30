package com.teamzmron.dagger2sample.di

import com.teamzmron.dagger2sample.di.auth.AuthModule
import com.teamzmron.dagger2sample.di.auth.AuthScope
import com.teamzmron.dagger2sample.di.auth.AuthViewModelsModule
import com.teamzmron.dagger2sample.di.main.MainFragmentBuildersModule
import com.teamzmron.dagger2sample.di.main.MainModule
import com.teamzmron.dagger2sample.di.main.MainScope
import com.teamzmron.dagger2sample.di.main.MainViewModelsModule
import com.teamzmron.dagger2sample.ui.auth.AuthActivity
import com.teamzmron.dagger2sample.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun contributeAuthActivity() : AuthActivity

    @MainScope
    @ContributesAndroidInjector(
         modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}