package com.example.cynix.dagger.modules

import com.example.cynix.activity.CharacterDetailsActivity
import com.example.cynix.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun contributeDetailActivity(): CharacterDetailsActivity
}