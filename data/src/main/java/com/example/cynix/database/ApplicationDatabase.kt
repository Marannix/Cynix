package com.example.cynix.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cynix.characters.dao.CharacterDao
import com.example.cynix.characters.entity.CharactersEntity

@Database(
    entities = [CharactersEntity::class],
    version = 3
)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
}