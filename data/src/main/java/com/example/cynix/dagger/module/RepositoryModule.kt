package com.example.cynix.dagger.module

import com.example.cynix.character.CharacterRepository
import com.example.cynix.characters.CharacterRepositoryImpl
import com.example.cynix.characters.dao.CharacterDao
import com.example.cynix.remote.CharacterApi
import dagger.Module
import dagger.Provides

@Module
 class RepositoryModule {

    @Provides
    fun provideCharacterRepository(characterApi: CharacterApi, characterDao: CharacterDao) : CharacterRepository {
        return CharacterRepositoryImpl(characterDao, characterApi)
    }
}