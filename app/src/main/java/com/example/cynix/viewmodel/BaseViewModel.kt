package com.example.cynix.viewmodel

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

// S = State
// E = Event
open class BaseViewModel : ViewModel() {

//    private val reducers = PublishRelay.create<Reducer<S>>().toSerialized()
//    private val events = PublishRelay.create<E>()
    private val compositeDisposable = CompositeDisposable()


    protected fun Disposable.addDisposable() = compositeDisposable.add(this)


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

//interface Reducer<S> {
//    fun reduce(oldState: S): S
//
//    companion object {
//        // What does op mean?
//        inline operator fun <S> invoke(crossinline op: (s: S) -> S) =
//            object : Reducer<S> {
//                override fun reduce(oldState: S): S {
//                    return op(oldState)
//                }
//            }
//    }
//}
