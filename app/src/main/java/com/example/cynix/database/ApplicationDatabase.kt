package com.example.cynix.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cynix.data.Converters
import com.example.cynix.data.characters.CharactersDao
import com.example.cynix.data.characters.CharactersResults

@Database(
    entities = [CharactersResults::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}