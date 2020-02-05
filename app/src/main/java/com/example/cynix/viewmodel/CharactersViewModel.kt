package com.example.cynix.viewmodel

import com.example.cynix.common.AsyncResult
import com.example.cynix.data.entity.CharactersEntity
import com.example.cynix.state.CharactersState
import com.example.cynix.usecase.CharacterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) : BaseViewModel<CharactersState, CharactersViewModel.CharacterEvent>(CharactersState()) {

    init {
        getCharacters()
    }

    private fun getCharacters() {
        characterUseCase.getCharacterDataState()
            .observeOn(AndroidSchedulers.mainThread())
            .map { characterDataState ->
                return@map when (characterDataState) {
                    is CharacterUseCase.CharacterDataState.Success -> {
                        AsyncResult.Success(characterDataState.characters)

                    }
                    is CharacterUseCase.CharacterDataState.Error -> {
                        AsyncResult.Error(characterDataState.errorMessage)
                    }
                }
            }
            .startWith(AsyncResult.Loading)
            .subscribe { state ->
                CharactersState(state)
                applyState(Reducer { it.copy(characters = state) })
            }
            .addDisposable()
    }


    sealed class CharacterEvent {
        object GenericErrorEvent : CharacterEvent()
    }

}