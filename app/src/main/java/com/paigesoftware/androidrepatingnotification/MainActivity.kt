package com.paigesoftware.androidrepatingnotification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        button.setOnClickListener {
            Toast.makeText(this, "Remidner Set!", Toast.LENGTH_LONG).show()

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 22)
            calendar.set(Calendar.MINUTE, 6)

            val intent = Intent(this, ReminderBroadcast::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = getSystemService(ALARM_SERVICE) as? AlarmManager
            alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }

    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "notify channel"
            val description = "channel for test"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel =  NotificationChannel("notify", name, importance)
            notificationChannel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

}