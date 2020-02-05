package com.example.cynix.state

import com.example.cynix.common.AsyncResult
import com.example.cynix.data.entity.CharactersEntity

data class CharactersState(
    val characters: AsyncResult<List<CharactersEntity>>? = null
) {
    val isLoading: Boolean?
        get() = characters is AsyncResult.Loading
}
