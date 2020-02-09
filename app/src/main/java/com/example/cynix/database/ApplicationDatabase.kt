package com.example.cynix.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cynix.data.Converters
import com.example.cynix.data.dao.CharactersDao
import com.example.cynix.data.characters.CharactersResults
import com.example.cynix.data.entity.CharactersEntity

@Database(
    entities = [CharactersEntity::class],
    version = 3
)
//TODO: Need to inject the new characterDao
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}