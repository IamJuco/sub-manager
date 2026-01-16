package com.juco.submanager.app

import android.app.Application
import com.juco.submanager.BuildConfig
import com.juco.submanager.app.util.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.google.android.gms.ads.MobileAds
import com.juco.work.NotificationUtil
import com.juco.work.SubscriptionWorkerUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(this@SubManagerApplication) {}
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}