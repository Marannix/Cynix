package com.example.cynix.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cynix.dagger.ViewModelFactory
import com.example.cynix.dagger.ViewModelKey
import com.example.cynix.viewmodel.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    internal abstract fun bindingCharactersViewModel(viewModel: CharactersViewModel): ViewModel

}