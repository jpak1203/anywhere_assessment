package com.jpakku.anywhereapplication.di

import android.app.Application
import com.jpakku.anywhereapplication.AnywhereApplication
import com.jpakku.anywhereapplication.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    ActivityModule::class,
    FragmentModule::class,
    ViewModelModule::class,
    NetworkModule::class,])
interface AppComponent: AndroidInjector<AnywhereApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
