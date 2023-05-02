package com.example.alarmapp.AlarmList

import com.example.alarmapp.model.Alarm

interface OnClickAlarmListener {

    fun onClick(alarm: Alarm)

    fun onLongClick(alarm: Alarm)
}