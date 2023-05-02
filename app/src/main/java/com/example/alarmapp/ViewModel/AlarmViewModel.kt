package com.example.alarmapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.alarmapp.model.Alarm
import com.example.alarmapp.model.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: AlarmRepository
    var list: LiveData<List<Alarm>>

    init {
        repository = AlarmRepository(application)

        list = repository.list
    }

    fun insert(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.insert(alarm)
        }
    }

    fun delete(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.delete(alarm)
        }
    }

    fun update(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.update(alarm)
        }
    }
}


//https://www.youtube.com/watch?v=dcGFcli9X-c&list=PLGcXVZF_4NLOA606dwVkaMiUWlhc8J_5S

//https://www.youtube.com/watch?v=OjlU71o-EUo&list=PLpOknmJ06s1uGTUR0QuxoSIbDQNNul9MO

//https://www.youtube.com/watch?v=f06A3rCPWxg&list=PLCfiPrbaKKQW-IrCWww_INr_2STCDnEhN

//https://www.youtube.com/watch?v=b5ma-vn7lb8&list=PLWYv6puWqP0NmF44MLES7oi5U0H_XZrJa

//https://www.youtube.com/watch?v=eYmBfgqPw50&list=PLshu09rTUyFPQc9NGrQbBDYhHnQiflIT7

//https://www.youtube.com/watch?v=5o5xXQhJVhQ&list=PLVLrPTnNLi1TRNgTSYqWzGHiH8_rISKq6