package com.teamzmron.dagger2sample.di.main

import com.teamzmron.dagger2sample.network.main.MainApi
import com.teamzmron.dagger2sample.ui.main.posts.PostRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit) : MainApi = retrofit.create(MainApi::class.java )

    @MainScope
    @Provides
    fun provideAdapter() : PostRecyclerAdapter = PostRecyclerAdapter()

}