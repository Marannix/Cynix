package com.example.cynix.character

import io.reactivex.Observable

interface CharacterRepository {
    fun getCharacters() : Observable<List<Character>>
}