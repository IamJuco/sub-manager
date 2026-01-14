package com.juco.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.juco.common.util.Logger
import com.juco.common.util.nextPaymentCalculator
import com.juco.local.repository.LocalRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SubscriptionNotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: LocalRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val subscriptions = repository.getActiveSubscriptions()
            val messageList = mutableListOf<String>()
            subscriptions.forEach { sub ->
                val calcResult = nextPaymentCalculator(
                    sub.paymentDay,
                    sub.paymentCycleType,
                    sub.paymentCycleValue
                )
                if (calcResult.dDay == "D-Day") {
                    messageList.add("[${sub.name}] ${sub.price}원 오늘 결제 💸")
                } else if (calcResult.dDay == "D-1") {
                    messageList.add("[${sub.name}] ${sub.price}원 내일 결제 📅")
                }
            }

            if (messageList.isNotEmpty()) {
                NotificationUtil.showPaymentNotification(context, messageList)
            }
            Result.success()
        } catch (e: Exception) {
            Logger.e("0526Worker", e.message.toString())
            Result.retry()
        }
    }
}