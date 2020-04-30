package com.teamzmron.dagger2sample.di.auth

import androidx.lifecycle.ViewModel
import com.teamzmron.dagger2sample.di.ViewModelKey
import com.teamzmron.dagger2sample.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel) : ViewModel

}