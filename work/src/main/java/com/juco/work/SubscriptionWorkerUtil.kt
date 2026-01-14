package com.juco.work

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

object SubscriptionWorkerUtil {
    private const val WORK_NAME = "DailySubscriptionNotification"

    fun enqueueDailyWork(context: Context) {
        val initialDelay = calculateInitialDelayFor9AM()
        val workRequest = PeriodicWorkRequestBuilder<SubscriptionNotificationWorker>(
            24, TimeUnit.HOURS
        )
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .addTag("SUB_NOTIFICATION")
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun calculateInitialDelayFor9AM(): Long {
        val currentTime = Calendar.getInstance()

        val targetTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (targetTime.before(currentTime)) {
            targetTime.add(Calendar.DAY_OF_YEAR, 1)
        }

        return targetTime.timeInMillis - currentTime.timeInMillis
    }
}