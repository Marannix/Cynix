package com.example.cynix.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterApi {

    @GET("character/?page=1")
    fun getCharacters(): Observable<CharacterApiContract.CharactersResponse>

    @GET
    fun getNextCharacters(@Url nextCharactersUrl: String): Observable<CharacterApiContract.CharactersResponse>

}
