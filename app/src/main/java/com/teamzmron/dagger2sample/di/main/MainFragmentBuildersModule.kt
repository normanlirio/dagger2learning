package com.teamzmron.dagger2sample.di.main

import com.teamzmron.dagger2sample.ui.main.posts.PostsFragment
import com.teamzmron.dagger2sample.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment() : PostsFragment
}