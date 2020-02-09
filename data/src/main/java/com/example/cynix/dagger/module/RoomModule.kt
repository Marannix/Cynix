package com.example.cynix.dagger.module

import android.app.Application
import androidx.room.Room
import com.example.cynix.characters.dao.CharacterDao
import com.example.cynix.database.ApplicationDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): ApplicationDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ApplicationDatabase::class.java, "rickandmorty.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharactersDao(applicationDatabase: ApplicationDatabase): CharacterDao {
        return applicationDatabase.charactersDao()
    }
}