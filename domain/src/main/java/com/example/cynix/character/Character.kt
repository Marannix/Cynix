package com.example.cynix.character

data class Character (
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: Location,
    val episode: List<String>
)