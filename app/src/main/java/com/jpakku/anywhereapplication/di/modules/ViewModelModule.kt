package com.jpakku.anywhereapplication.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jpakku.anywhereapplication.activities.MainActivityViewModel
import com.jpakku.anywhereapplication.di.factories.ViewModelFactory
import com.jpakku.anywhereapplication.di.factories.ViewModelKey
import com.jpakku.anywhereapplication.ui.list.ListFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    internal abstract fun bindListFragmentViewModel(viewModel: ListFragmentViewModel): ViewModel

}