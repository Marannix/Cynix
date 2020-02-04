package com.example.cynix.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.example.cynix.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainActivity(activity: MainActivity): FragmentActivity


}