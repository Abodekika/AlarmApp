package com.example.alarmapp.Service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.alarmapp.Application.App
import com.example.alarmapp.R
import com.example.alarmapp.RingAlarm

class AlarmService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.azannnn)
        mediaPlayer?.isLooping = true
        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val intent1 = Intent(this, RingAlarm::class.java)

     //   intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_MUTABLE)


        val notification = NotificationCompat.Builder(this, App.ID)
            .setSmallIcon(R.drawable.ic_baseline_access_time_24)
            .setContentTitle("Wake Up")
            .setContentText("Please! Wake Up")
            .setContentIntent(pendingIntent)
            .setSound(null)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setFullScreenIntent(pendingIntent,true)

            .build()

        mediaPlayer!!.start()

        val pattern = longArrayOf(0, 100, 1000)
        vibrator!!.vibrate(pattern, 0)
        startForeground(1, notification)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        vibrator!!.cancel()
    }
}