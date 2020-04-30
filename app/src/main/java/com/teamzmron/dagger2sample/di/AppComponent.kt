package com.teamzmron.dagger2sample.di

import android.app.Application
import com.teamzmron.dagger2sample.BaseApplication
import com.teamzmron.dagger2sample.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component (
    modules = [AndroidSupportInjectionModule::class, ActivityBuildersModule::class,
        AppModule::class, ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager?

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application:Application) : Builder

        fun build() : AppComponent
    }
}