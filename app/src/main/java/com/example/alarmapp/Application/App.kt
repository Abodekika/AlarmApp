package com.example.alarmapp.Application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class App : Application() {

    companion object {
        val ID = "com.example.alarmapp.Ahmed"
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {

        val channel =
            NotificationChannel(ID, "Alarm Service", NotificationManager.IMPORTANCE_HIGH)
        channel.description = ("Ahmed Ahmed")

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}