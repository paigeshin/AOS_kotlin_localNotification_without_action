package com.paigesoftware.androidrepatingnotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/*
Side Notes

According to the official documentation, starting in android 8.0 (API Level 26), all notifications must be assigned to a channel.

* */

class ReminderBroadcast: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationBuilder = NotificationCompat.Builder(context!!, "notify")
        notificationBuilder
            .setSmallIcon(android.R.drawable.alert_dark_frame)
            .setContentTitle("remind me")
            .setContentText("Hello students")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(200, notificationBuilder.build())
    }

}