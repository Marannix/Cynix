package com.example.cynix.dagger.module

import android.app.Application
import androidx.room.Room
import com.example.cynix.characters.dao.CharacterDao
import com.example.cynix.database.ApplicationDatabase2
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): ApplicationDatabase2 {
        return Room.databaseBuilder(
            application.applicationContext,
            ApplicationDatabase2::class.java, "rickandmorty.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharactersDao(applicationDatabase: ApplicationDatabase2): CharacterDao {
        return applicationDatabase.charactersDao()
    }
}