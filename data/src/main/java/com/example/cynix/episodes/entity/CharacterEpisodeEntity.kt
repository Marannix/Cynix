package com.example.cynix.episodes.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characterEpisodes")
data class CharacterEpisodeEntity(
    @NonNull @PrimaryKey
    @ColumnInfo(name = "id")  val id: String,
    @ColumnInfo(name = "characterId") var characterId: String,
    @ColumnInfo(name = "name")  val name: String,
    @ColumnInfo(name = "airdate")  val airdate: String,
    @ColumnInfo(name = "episode")  val episode: String
)