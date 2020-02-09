package com.example.cynix.viewmodel

import android.util.Log
import com.example.cynix.character.usecase.ObserveCharacterUseCase
import com.example.cynix.common.AsyncResult
import com.example.cynix.data.entity.CharactersEntity
import com.example.cynix.state.CharactersState

import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CharactersViewModel2 @Inject constructor(
    private val characterUseCase: ObserveCharacterUseCase
) : BaseViewModel<CharactersState, CharactersViewModel2.CharacterEvent>(CharactersState()) {

    init {
        getCharacters()
    }

    private fun getCharacters() {
//        characterUseCase.getCharacterDataState()
//            .observeOn(AndroidSchedulers.mainThread())
//            .map { characterDataState ->
//                return@map when (characterDataState) {
//                    is CharacterUseCase.CharacterDataState.Success -> {
//                        AsyncResult.Success(characterDataState.characters)
//
//                    }
//                    is CharacterUseCase.CharacterDataState.Error -> {
//                        AsyncResult.Error(characterDataState.errorMessage)
//                    }
//                }
//            }
//            .startWith(AsyncResult.Loading)
//            .subscribe { state ->
//                CharactersState(state)
//                applyState(Reducer { it.copy(characters = state) })
//            }
//            .addDisposable()

        characterUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("stuff", it[2].toString())
            }, {
                Log.d("error", it.message)
            })
            .addDisposable()
    }


    sealed class CharacterEvent {
        object GenericErrorEvent : CharacterEvent()
    }

}