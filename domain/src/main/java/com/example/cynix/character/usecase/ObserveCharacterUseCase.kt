package com.example.cynix.character.usecase

import com.example.cynix.character.Character
import com.example.cynix.character.CharacterRepository
import io.reactivex.Observable
import javax.inject.Inject

class ObserveCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {
    operator fun invoke(): Observable<List<Character>> {
        return repository.getCharacters()
    }
}