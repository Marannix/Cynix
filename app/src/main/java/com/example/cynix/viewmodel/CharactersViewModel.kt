package com.example.cynix.viewmodel

import android.util.Log
import com.example.cynix.character.Character
import com.example.cynix.character.usecase.ObserveCharacterUseCase
import com.example.cynix.common.AsyncResult
import io.reactivex.Observable

import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val characterUseCase: ObserveCharacterUseCase
) : BaseViewModel<CharactersState, CharactersViewModel.CharacterEvent>(CharactersState()) {


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
            .mapToAsyncResult()
            .subscribe({ result ->
                CharactersState(result)
                applyState(Reducer { it.copy(characters = result) })
                Log.d("stuff", result.toString())
            }, {
                Log.d("error", it.message)
            })
            .addDisposable()
    }


    sealed class CharacterEvent {
        object GenericErrorEvent : CharacterEvent()
    }

}


fun <T> Observable<T>.mapToAsyncResult(): Observable<AsyncResult<T>> {
    return this.map<AsyncResult<T>> { data -> AsyncResult.Success(data) }
        .onErrorReturn { e -> AsyncResult.Error(e.message) }
        .startWith(AsyncResult.Loading)
}

data class CharactersState(
    val characters: AsyncResult<List<Character>>? = null
) {
    val isLoading: Boolean?
        get() = characters is AsyncResult.Loading
}

