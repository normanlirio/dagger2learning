package com.teamzmron.dagger2sample.di.main

import androidx.lifecycle.ViewModel
import com.teamzmron.dagger2sample.di.ViewModelKey
import com.teamzmron.dagger2sample.ui.main.posts.PostsViewModel
import com.teamzmron.dagger2sample.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel) : ViewModel

}