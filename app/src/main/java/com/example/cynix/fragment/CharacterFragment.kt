package com.example.cynix.fragment

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cynix.R
import com.example.cynix.adapter.CharacterAdapter
import com.example.cynix.dialog.FullscreenLoadingDialog
import com.example.cynix.viewmodel.CharactersViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : BaseFragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var viewmodel: CharactersViewModel
    private lateinit var loadingDialog: Dialog
    private fun Disposable.addDisposable() = disposables.add(this)
    private val disposables = CompositeDisposable()

    private val charactersAdapter = CharacterAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        stuff()
        setAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    fun stuff() {
        viewmodel =
            ViewModelProviders.of(this, viewModelFactory).get(CharactersViewModel::class.java)

        loadingDialog = FullscreenLoadingDialog(requireContext()).apply {
            setCanceledOnTouchOutside(false)
        }

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
                    charactersAdapter.setData(it)
                    Log.d("Characters ->", state.listOfCharacters.size.toString())
                }

                state.isLoading.let {
                    when (it) {
                        true -> loadingDialog.show()
                        false -> loadingDialog.hide()
                    }
                }

            }.addDisposable()
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = charactersAdapter
    }

     interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterFragment()
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}
