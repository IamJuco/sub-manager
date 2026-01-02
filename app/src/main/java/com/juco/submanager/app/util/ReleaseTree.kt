package com.juco.submanager.app.util

import android.util.Log
import org.jetbrains.annotations.NotNull
import timber.log.Timber

class ReleaseTree : @NotNull Timber.Tree() {
    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?,
    ) {
        // 추후 Release 버전에서 crashlytics에 보낼 로그
        if (priority == Log.ERROR || priority == Log.WARN) {

        }
    }
}