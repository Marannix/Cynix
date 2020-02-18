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
import com.example.cynix.character.Character
import com.example.cynix.dialog.FullscreenLoadingDialog
import com.example.cynix.viewmodel.CharactersViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : BaseFragment() {

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(character: Character)
    }

    private lateinit var viewmodel: CharactersViewModel
    private lateinit var loadingDialog: Dialog

    private var listener: OnFragmentInteractionListener? = null
    private val disposables = CompositeDisposable()
    private val charactersAdapter = CharacterAdapter()

    private fun Disposable.addDisposable() = disposables.add(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadingDialog = FullscreenLoadingDialog(requireContext()).apply {
            setCanceledOnTouchOutside(false)
        }
        createViewModel()
        setAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    private fun createViewModel() {
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

                state.listOfCharacters.let {
                    charactersAdapter.setData(it)
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

        charactersAdapter.setItemClickListener(object : CharacterAdapter.ItemClickListener {
            override fun onClick(charactersResults: Character) {
                listener?.onFragmentInteraction(charactersResults)
            }
        })
    }

    fun setItemClickListener(listener: OnFragmentInteractionListener) {
        this.listener = listener
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}
