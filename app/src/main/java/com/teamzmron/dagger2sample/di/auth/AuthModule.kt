package com.teamzmron.dagger2sample.di.auth

import com.teamzmron.dagger2sample.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi = retrofit.create(AuthApi::class.java)
}