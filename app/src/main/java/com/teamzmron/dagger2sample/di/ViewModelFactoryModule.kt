package com.teamzmron.dagger2sample.di

import androidx.lifecycle.ViewModelProvider
import com.teamzmron.dagger2sample.ViewModels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory) : ViewModelProvider.Factory

}