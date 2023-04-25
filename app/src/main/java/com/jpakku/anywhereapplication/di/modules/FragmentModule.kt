package com.jpakku.anywhereapplication.di.modules

import com.jpakku.anywhereapplication.ui.detail.DetailFragment
import com.jpakku.anywhereapplication.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributesListFragment(): ListFragment

    @ContributesAndroidInjector
    internal abstract fun contributesDetailFragment(): DetailFragment
}