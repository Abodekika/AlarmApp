package com.example.alarmapp.CreateAlarm

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.alarmapp.R
import com.example.alarmapp.ViewModel.AlarmViewModel
import com.example.alarmapp.databinding.FragmentCreateBinding
import com.example.alarmapp.model.Alarm
import com.google.android.material.button.MaterialButtonToggleGroup


class CreateFragment : Fragment() {

    lateinit var binding: FragmentCreateBinding
    lateinit var viewModel: AlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AlarmViewModel::class.java]

        val alarm = Alarm()

        binding.toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            when (checkedId) {
                binding.btnM.id -> alarm.mon = isChecked
                binding.btnTue.id -> alarm.tue = isChecked
                binding.btnWeb.id -> alarm.web = isChecked
                binding.btnThr.id -> alarm.thu = isChecked
                binding.btnFri.id -> alarm.fri = isChecked
                binding.btnSat.id -> alarm.sat = isChecked
                binding.btnSun.id -> alarm.sun = isChecked
            }
        }

        binding.btnCreate.setOnClickListener {

            alarm.hour = TimePickerUtil.getHour(binding.timePicker)
            alarm.minute = TimePickerUtil.getMinute(binding.timePicker)
            alarm.schedule(requireContext())
            viewModel.insert(alarm)

            Navigation.findNavController(binding.btnCreate)
                .navigate(R.id.action_createFragment_to_alarmListFragment)
        }

    }
}