package com.example.alarmapp.AlarmList

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarmapp.R
import com.example.alarmapp.Service.AlarmService
import com.example.alarmapp.ViewModel.AlarmViewModel
import com.example.alarmapp.databinding.FragmentAlarmListBinding
import com.example.alarmapp.model.Alarm


class AlarmListFragment : Fragment(), OnToggleListener, OnClickAlarmListener {

    private lateinit var binding: FragmentAlarmListBinding
    private lateinit var adapter: AdapterAlarm
    lateinit var viewModel: AlarmViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlarmListBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AlarmViewModel::class.java]
        viewModel.list.observe(viewLifecycleOwner) {
            it
            adapter.setdata(it)
        }
        adapter = AdapterAlarm()
        adapter.addOnToggleListener(this)
        adapter.addOnclickAlarmListener(this)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.floatingActionButton.setOnClickListener {

//            val intent = Intent(context, AlarmService::class.java)
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                context?.startForegroundService(intent)
//            } else {
//                context?.startService(intent)
//            }
            Navigation.findNavController(binding.floatingActionButton)
                .navigate(R.id.action_alarmListFragment_to_createFragment)
        }
    }

    override fun onToggle(alarm: Alarm) {
        if (alarm.start) {
            alarm.schedule(requireContext())

        } else {
            alarm.cancel(requireContext())
        }
    }

    override fun onClick(alarm: Alarm) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(alarm: Alarm) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delete Item")
            .setMessage("Do you want delete")
            .setNegativeButton("No") { dialog, which ->

                dialog.dismiss()
            }
            .setPositiveButton("yes") { dialog, which ->
                if (alarm.start) {
                    alarm.cancel(requireContext())
                }
                viewModel.delete(alarm)
            }


        dialog.show()

    }
}