package com.della.della_mobile.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.della.della_mobile.DellaApplication
import com.della.della_mobile.R

object NotificationUtil {
    private const val CHANNEL_ID = "1"
    private val context: Context
        get() {
            return DellaApplication.getInstance().applicationContext
        }

    fun createChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val manager = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            val appName = context.getString(R.string.app_name)
            val channel = NotificationChannel(
                CHANNEL_ID,
                appName,
                NotificationManager.IMPORTANCE_HIGH
            )

            manager.createNotificationChannel(channel)
        }
    }

    fun create(id: Int, intent: Intent, title: String, content: String) {
        createChannel()

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        with(NotificationManagerCompat.from(DellaApplication.getInstance())) {
            val notification = builder.build()
            notify(id, notification)
        }
    }

}