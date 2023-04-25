package com.jpakku.anywhereapplication.util

import androidx.annotation.StyleRes
import com.jpakku.anywhereapplication.BuildConfig
import com.jpakku.anywhereapplication.R
import timber.log.Timber
import java.util.*
import javax.inject.Singleton

@Singleton
class AppConfigUtil {

    companion object {
        const val TheSimpsons = "simpsons"
        const val TheWire = "thewire"
    }

    private sealed class BuildClient(@StyleRes val theme: Int) {
        object TheSimpsons: BuildClient(R.style.Theme_TheSimpsons)
        object TheWire: BuildClient(R.style.Theme_TheWire)
    }

    private val buildClient by lazy {
        val flavor = BuildConfig.FLAVOR.lowercase(Locale.ROOT)
        Timber.i("app configuration default: $flavor")
        when (flavor) {
            TheSimpsons -> BuildClient.TheSimpsons
            TheWire -> BuildClient.TheWire
            else -> BuildClient.TheSimpsons
        }
    }

    fun getClientTheme(): Int = buildClient.theme

}