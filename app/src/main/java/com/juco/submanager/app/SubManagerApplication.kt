package com.juco.submanager.app

import android.app.Application
import com.juco.submanager.BuildConfig
import com.juco.submanager.app.util.ReleaseTree
import timber.log.Timber

class SubManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}