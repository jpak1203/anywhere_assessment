package com.jpakku.anywhereapplication.di.modules

import android.app.Application
import android.content.Context
import com.jpakku.anywhereapplication.AnywhereApplication
import com.jpakku.anywhereapplication.util.AppConfigUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApplication(app: Application): AnywhereApplication = (app as AnywhereApplication)

    @Singleton
    @Provides
    fun providesContext(anywhereApplication: AnywhereApplication): Context {
        return anywhereApplication.applicationContext
    }

    @Singleton
    @Provides
    fun provideThemeHelperUtil(): AppConfigUtil {
        return AppConfigUtil()
    }
}