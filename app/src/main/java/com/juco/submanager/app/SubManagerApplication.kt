package com.juco.submanager.app

import android.app.Application
import com.juco.submanager.BuildConfig
import com.juco.submanager.app.util.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.juco.work.NotificationUtil
import com.juco.work.SubscriptionWorkerUtil

@HiltAndroidApp
class SubManagerApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        initTimber()
        NotificationUtil.createNotificationChannel(this)
        SubscriptionWorkerUtil.enqueueDailyWork(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}