package com.example.cynix.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cynix.R

import com.example.cynix.viewmodel.CharactersViewModel2
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewmodel: CharactersViewModel2
    private val disposables = CompositeDisposable()

    private fun Disposable.addDisposable() = disposables.add(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel =
            ViewModelProviders.of(this, viewModelFactory).get(CharactersViewModel2::class.java)

//        viewmodel.events()
//            .subscribe {
//                when (it) {
//                    is CharactersViewModel.CharacterEvent.GenericErrorEvent -> {
//                        Log.d("error -> ", "event error")
//                    }
//                }
//            }.addDisposable()
//
//        viewmodel.states()
//            .distinctUntilChanged()
//            .subscribe { state ->
//                Log.d("loading", state.isLoading.toString())
//                Log.d("charactersState", state.characters.toString())
//            }.addDisposable()

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}