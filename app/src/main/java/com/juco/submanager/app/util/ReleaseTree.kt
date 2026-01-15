package com.juco.submanager.app.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.log("[$tag] $message")

        if (priority == Log.ERROR || priority == Log.WARN) {
            if (t != null) {
                crashlytics.recordException(t)
            } else {
                crashlytics.recordException(Exception(message))
            }
        }
    }
}