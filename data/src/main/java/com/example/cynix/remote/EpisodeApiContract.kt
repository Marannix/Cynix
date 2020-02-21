package com.example.cynix.remote

import com.google.gson.annotations.SerializedName

interface EpisodeApiContract {

    data class EpisodeResponse(
        @SerializedName("info")
        val episodesPageInfo: EpisodePageInfo,
        @SerializedName("results")
        val episodes: List<EpisodesResult>
    )

    data class EpisodePageInfo (
        val count: Int,
        val pages: Int,
        val next: String,
        val prev: String
    )

    data class EpisodesResult(
        val id: String,
        val name: String,
        @SerializedName("air_Date")
        val airdate: String,
        @SerializedName("characters")
        val charactersUrls: List<String>,
        @SerializedName("url")
        val episodeUrl: String,
        val created: String
    )

    data class CharacterEpisodeResponse(
        val id: String,
        var characterId: String,
        val name: String,
        @SerializedName("air_date")
        val airdate: String,
        val episode: String
    )
}