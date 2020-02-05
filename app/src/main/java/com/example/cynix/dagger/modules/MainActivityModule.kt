package com.example.cynix.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.example.cynix.activity.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainActivity(activity: MainActivity): FragmentActivity


}