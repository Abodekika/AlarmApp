package com.example.alarmapp.AlarmList

import com.example.alarmapp.model.Alarm

interface OnToggleListener {

    fun onToggle(alarm: Alarm)
}