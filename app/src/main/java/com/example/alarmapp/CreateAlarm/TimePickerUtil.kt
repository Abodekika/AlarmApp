package com.example.alarmapp.CreateAlarm

import android.os.Build
import android.widget.TimePicker

class TimePickerUtil {
    companion object {
        fun getHour(timepicker: TimePicker): Int {

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                timepicker.hour
            } else {
                timepicker.currentHour
            }

        }

        fun getMinute(timepicker: TimePicker): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                timepicker.minute
            } else {
                timepicker.currentMinute
            }
        }
    }
}