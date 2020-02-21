package com.example.cynix.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface EpisodeApi {

    @GET
    fun getCharacterEpisodes(@Url episodeUrl: String): Observable<EpisodeApiContract.CharacterEpisodeResponse>

    @GET
    fun getNextEpisodes(@Url nextEpisodesUrl: String): Observable<EpisodeApiContract.EpisodeResponse>
}