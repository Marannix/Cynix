package com.example.cynix.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cynix.R

import com.example.cynix.viewmodel.CharactersViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewmodel: CharactersViewModel
    private val disposables = CompositeDisposable()

    private fun Disposable.addDisposable() = disposables.add(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel =
            ViewModelProviders.of(this, viewModelFactory).get(CharactersViewModel::class.java)

        viewmodel.events()
            .subscribe {
                when (it) {
                    is CharactersViewModel.CharacterEvent.GenericErrorEvent -> {
                       showToast(getString(R.string.generic_error_description))
                    }
                }
            }.addDisposable()

        viewmodel.states()
            .distinctUntilChanged()
            .subscribe { state ->
                Log.d("loading", state.isLoading.toString())

                state.listOfCharacters.let {
                    Log.d("Characters ->", state.listOfCharacters.size.toString())
                }

            }.addDisposable()

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}