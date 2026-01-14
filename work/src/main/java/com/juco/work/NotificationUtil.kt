package com.juco.work

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.juco.submanager.core.designsystem.R

object NotificationUtil {
    private const val CHANNEL_ID = "sub_payment_channel"
    private const val CHANNEL_NAME = "구독 결제 알림"
    private const val GROUP_ID = 1001
    private const val TITLE = "구독매니저"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = "결제일이 도래한 구독 알림"
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun showPaymentNotification(context: Context, messages: List<String>) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                return
            }
        }

        val launchIntent = context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            GROUP_ID,
            launchIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_setting_fill_true)
            .setContentTitle(TITLE)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (messages.size == 1) {
            builder.setContentText(messages.first())
        } else {
            val inboxStyle = NotificationCompat.InboxStyle()
                .setBigContentTitle("$TITLE (총 ${messages.size}건)")
                .setSummaryText("결제 예정 알림")
            messages.forEach { msg ->
                inboxStyle.addLine(msg)
            }

            builder.setStyle(inboxStyle)
            builder.setContentText("총 ${messages.size}건의 결제 예정 내역이 있습니다.")
        }

        NotificationManagerCompat.from(context).notify(GROUP_ID, builder.build())
    }
}