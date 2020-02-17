package com.example.cynix.character

import com.example.cynix.common.AsyncResult

data class CharactersState(
    val characters: AsyncResult<List<Character>>? = null
) {
    val isLoading: Boolean?
        get() = characters is AsyncResult.Loading

    val listOfCharacters : List<Character>
        get() = if (characters is AsyncResult.Success && characters.characters != null) {
            characters.characters
        } else {
            emptyList()
        }
}