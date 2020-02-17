package com.example.cynix.common

import io.reactivex.Observable

sealed class AsyncResult<out T> {
    object Loading : AsyncResult<Nothing>()
    data class Success<out T>(val characters: T? = null) : AsyncResult<T>()
    data class Error(val errorMessage: String?) : AsyncResult<Nothing>()
}

fun <T> Observable<T>.mapToAsyncResult(): Observable<AsyncResult<T>> {
    return this.map<AsyncResult<T>> { data -> AsyncResult.Success(data) }
        .onErrorReturn { e -> AsyncResult.Error(e.message) }
        .startWith(AsyncResult.Loading)
}
