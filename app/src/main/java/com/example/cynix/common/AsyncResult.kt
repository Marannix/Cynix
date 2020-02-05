package com.example.cynix.common

sealed class AsyncResult<out T> {
    object Loading : AsyncResult<Nothing>()
    data class Success<out T>(val characters: T? = null) : AsyncResult<T>()
    data class Error(val errorMessage: String?) : AsyncResult<Nothing>()
}