package com.example.alarmapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmapp.Service.AlarmService
import com.example.alarmapp.databinding.ActivityRingAlarmBinding

class RingAlarm : AppCompatActivity() {

    private lateinit var binding: ActivityRingAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRingAlarmBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)

        } else {
            window.addFlags(
                FLAG_KEEP_SCREEN_ON
                        or FLAG_ALLOW_LOCK_WHILE_SCREEN_ON

            )
        }

        binding.button.setOnClickListener {

            val intent = Intent(this, AlarmService::class.java)
            applicationContext.stopService(intent)

            finish()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(false)
            setTurnScreenOn(false)
        } else {
            window.clearFlags(
                FLAG_KEEP_SCREEN_ON
                        or FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
            )
        }
    }
}