package com.example.cynix.characters.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cynix.remote.CharacterApiContract

@Entity(tableName = "characters")
data class CharactersEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "image") val image: String,
    @Embedded(prefix = "location_")
    val location: CharacterApiContract.CharacterLocation,
    @ColumnInfo(name = "episode") val episode: List<String>
)