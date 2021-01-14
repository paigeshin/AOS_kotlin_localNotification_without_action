# Broadcast Receiver

```kotlin
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
```

# Manifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.paigesoftware.androidrepatingnotification">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.AndroidRepatingNotification">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <receiver android:name=".ReminderBroadcast"/>
    </application>

</manifest>
```

# MainActivity

```kotlin
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
```