package com.example.cynix.remote

import com.google.gson.annotations.SerializedName

interface CharacterApiContract {

    data class CharactersResponse (
        @SerializedName("info")
        val characterPageInfo: CharactersPageInfo,
        @SerializedName("results")
        val charactersResults : List<CharactersResults>
    )

    data class CharactersPageInfo (
        val count: Int,
        val pages: Int,
        val next: String,
        val prev: String
    )

    data class CharactersResults (
        val id: String,
        val name: String,
        val status: String,
        val species: String,
        val gender: String,
        val image: String,
        val location: CharacterLocation,
        val episode: List<String>
    )

    data class CharacterLocation(
        val name: String
    )
}