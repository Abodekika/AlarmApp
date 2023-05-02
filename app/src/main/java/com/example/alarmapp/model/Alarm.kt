package com.example.alarmapp.model

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alarmapp.BroadCastReciver.AlarmBroadCastReceiver
import java.util.Calendar
import kotlin.random.Random

@Entity
class Alarm(
    @PrimaryKey(autoGenerate = true)
    var uid: Long,
    @ColumnInfo
    var hour: Int,

    @ColumnInfo
    var minute: Int,

    @ColumnInfo
    var mon: Boolean,

    @ColumnInfo
    var tue: Boolean,

    @ColumnInfo
    var web: Boolean,

    @ColumnInfo
    var thu: Boolean,

    @ColumnInfo
    var fri: Boolean,

    @ColumnInfo
    var sat: Boolean,

    @ColumnInfo
    var sun: Boolean,

    @ColumnInfo
    var start: Boolean,
) {

    constructor() : this(
        Random.nextLong(), 0, 0
    )

    constructor(id: Long?, hour: Int, minute: Int) : this(
        id!!,
        hour, minute, false, false, false, false, false, false, false, false
    )

    fun getTime(): String {
        return "$hour:$minute"
    }

    fun getRepeat(): String {
        var builder = StringBuilder()
        if (mon) {
            builder.append("Mon")
        }
        if (tue) {
            builder.append(",Tue")
        }
        if (web) {
            builder.append(",Web")
        }
        if (thu) {
            builder.append(",Thu")
        }
        if (fri) {
            builder.append(",Fri")
        }
        if (sat) {
            builder.append(",Sat")
        }
        if (sun) {
            builder.append(",Sun")
        }
        return builder.toString()
    }


    @RequiresApi(Build.VERSION_CODES.S)
    fun schedule(context: Context) {
        val alarmManager: AlarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadCastReceiver::class.java)

        intent.putExtra(AlarmBroadCastReceiver.MONDAY, mon)
        intent.putExtra(AlarmBroadCastReceiver.TUESDAY, tue)
        intent.putExtra(AlarmBroadCastReceiver.THURDAY, thu)
        intent.putExtra(AlarmBroadCastReceiver.WEDNEDAY, web)
        intent.putExtra(AlarmBroadCastReceiver.FRIDAY, fri)
        intent.putExtra(AlarmBroadCastReceiver.SATURDAY, sat)
        intent.putExtra(AlarmBroadCastReceiver.SUNDAY, sun)
        intent.putExtra(AlarmBroadCastReceiver.RECURRING, isLoop())


        val pendingIntent = PendingIntent.getBroadcast(
            context, uid.toInt(), intent, PendingIntent.FLAG_MUTABLE
        )

        val calender = Calendar.getInstance()
        calender.timeInMillis = System.currentTimeMillis()
        calender.set(Calendar.HOUR_OF_DAY, hour)
        calender.set(Calendar.MINUTE, minute)
        calender.set(Calendar.SECOND, 0)
        calender.set(Calendar.MILLISECOND, 0)

        if (calender.timeInMillis <= System.currentTimeMillis()) {
            calender.set(Calendar.DAY_OF_WEEK, calender.get(Calendar.DAY_OF_WEEK) + 1)

        }

        val oneDay: Long = 24 * 60 * 60 * 1000
        if (!isLoop()) {
           // alarmManager.setExact(AlarmManager.RTC_WAKEUP, calender.timeInMillis, pendingIntent)
            val alarmClockInfo = AlarmManager.AlarmClockInfo(calender.timeInMillis, pendingIntent)
            alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
        } else {
//            alarmManager.setRepeating(
//                AlarmManager.RTC_WAKEUP,
//                calender.timeInMillis,
//                oneDay,
//                pendingIntent
//            )
            val alarmClockInfo = AlarmManager.AlarmClockInfo(calender.timeInMillis, pendingIntent)
            alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
        }
        start = true
    }


    @RequiresApi(Build.VERSION_CODES.S)
    fun cancel(context: Context) {
        val alarmManager: AlarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadCastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, uid.toInt(), intent, PendingIntent.FLAG_MUTABLE)

        alarmManager.cancel(pendingIntent)
        start = false
    }

    private fun isLoop(): Boolean {
        return mon || tue || thu || web || fri || sat || sun
    }
}










