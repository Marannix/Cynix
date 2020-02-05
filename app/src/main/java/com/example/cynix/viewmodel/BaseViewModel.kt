package com.example.cynix.viewmodel

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observables.ConnectableObservable

// S = State
// E = Event
open class BaseViewModel<S, E>(initState: S) : ViewModel() {

    private val reducers = PublishRelay.create<Reducer<S>>().toSerialized()
    private val events = PublishRelay.create<E>()
    private val compositeDisposable = CompositeDisposable()


    private val store: ConnectableObservable<S> = reducers
        .scan(initState) { oldState, reducer -> reducer.reduce(oldState) }
        .replay(1)
        .apply { connect() }

    protected fun applyState(reducer: Reducer<S>) = reducers.accept(reducer)
    protected fun publish(e: E) = events.accept(e)
    protected fun currentState(): S = store.blockingFirst()

    protected fun Disposable.addDisposable() = compositeDisposable.add(this)

    fun states(): Observable<S> = store.observeOn(AndroidSchedulers.mainThread())
    fun events(): Observable<E> = events.observeOn(AndroidSchedulers.mainThread())

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

interface Reducer<S> {
    fun reduce(oldState: S): S

    companion object {
        // What does op mean?
        inline operator fun <S> invoke(crossinline op: (s: S) -> S) =
            object : Reducer<S> {
                override fun reduce(oldState: S): S {
                    return op(oldState)
                }
            }
    }
}
