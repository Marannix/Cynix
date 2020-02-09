package com.example.cynix.usecase

import com.example.cynix.data.entity.CharactersEntity

import io.reactivex.Observable
import javax.inject.Inject

//class CharacterUseCase @Inject constructor(
//    private val charactersRepository: CharactersRepository
//) {
//
//    fun getCharacterDataState(): Observable<CharacterDataState> {
//        return charactersRepository.getCharacters()
//            .map<CharacterDataState> { listOfCharacters ->
//                CharacterDataState.Success(listOfCharacters)
//            }
//            .onErrorReturn { error -> CharacterDataState.Error(error.message) }
//    }
//
//
//    sealed class CharacterDataState {
//        data class Success(val characters: List<CharactersEntity>) : CharacterDataState()
//        data class Error(val errorMessage: String?) : CharacterDataState()
//    }
//}