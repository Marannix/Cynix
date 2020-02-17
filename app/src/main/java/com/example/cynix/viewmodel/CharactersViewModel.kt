package com.example.cynix.viewmodel

import com.example.cynix.character.CharactersState
import com.example.cynix.character.usecase.ObserveCharacterUseCase
import com.example.cynix.common.AsyncResult
import com.example.cynix.common.mapToAsyncResult

import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val characterUseCase: ObserveCharacterUseCase
) : BaseViewModel<CharactersState, CharactersViewModel.CharacterEvent>(CharactersState()) {


    init {
        getCharacters()
    }

    private fun getCharacters() {
        characterUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .mapToAsyncResult()
            .startWith(AsyncResult.Loading)
            .subscribe { result ->
                CharactersState(result)
                applyState(Reducer { it.copy(characters = result) })
                if (result is AsyncResult.Error) publish(CharacterEvent.GenericErrorEvent)
            }
            .addDisposable()
    }

    sealed class CharacterEvent {
        object GenericErrorEvent : CharacterEvent()
    }
}
