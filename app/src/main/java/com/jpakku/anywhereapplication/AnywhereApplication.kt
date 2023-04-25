package com.jpakku.anywhereapplication

import android.app.Application
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.jpakku.anywhereapplication.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class AnywhereApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
        if (BuildConfig.DEBUG) {
            val logger = Timber.DebugTree()
            Timber.plant(logger)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}

@GlideModule
class ImageModule: AppGlideModule()
