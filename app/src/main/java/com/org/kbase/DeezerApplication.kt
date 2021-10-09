package com.org.kbase

import android.app.Application
import com.org.albumdetail.di.albumDetailModule
import com.org.core.app.di.initKoinDependencies
import com.org.kbase.app.di.appModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class DeezerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            // Debug build

            // init napier
            Napier.base(DebugAntilog("DeezerApplication"))
            initKoinDependencies(
                isDebug = BuildConfig.DEBUG,
                specificModules = listOf(appModule, albumDetailModule)
            ) {
                androidLogger()
                androidContext(this@DeezerApplication)
            }
        }

    }
}