package com.example.cynix.characters

import com.example.cynix.character.Character
import com.example.cynix.character.Location
import com.example.cynix.characters.entity.CharactersEntity

fun CharactersEntity.toDomain(charactersResults: CharactersEntity): Character {
    return Character(
        charactersResults.id,
        charactersResults.name,
        charactersResults.status,
        charactersResults.species,
        charactersResults.gender,
        charactersResults.image,
        Location(charactersResults.location.name),
        charactersResults.episode
    )
}