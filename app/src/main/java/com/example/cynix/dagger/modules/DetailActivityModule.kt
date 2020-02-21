package com.example.cynix.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.example.cynix.activity.CharacterDetailsActivity
import dagger.Binds
import dagger.Module

@Module
abstract class DetailActivityModule {

    @Binds
    abstract fun provideCharacterDetailsActivity(activity: CharacterDetailsActivity): FragmentActivity

}
