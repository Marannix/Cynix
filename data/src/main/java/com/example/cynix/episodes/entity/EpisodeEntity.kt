package com.example.cynix.episodes.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "airdate") val airdate: String,
    @ColumnInfo(name = "charactersUrl") val charactersUrls: List<String>,
    @ColumnInfo(name = "episodeUrl") val episodeUrl: String,
    @ColumnInfo(name = "created") val created: String
)