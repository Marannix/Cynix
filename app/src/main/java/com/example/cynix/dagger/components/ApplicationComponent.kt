package com.example.cynix.dagger.components

import android.app.Application
import com.example.cynix.MainApplication
import com.example.cynix.dagger.modules.ApiModule
import com.example.cynix.dagger.modules.ApplicationModule
import com.example.cynix.dagger.modules.ActivityBuilder
import com.example.cynix.dagger.modules.RoomModule
import com.example.cynix.dagger.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ActivityBuilder::class,
        ApiModule::class,
        RoomModule::class,
        ApplicationModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(application: MainApplication)
}