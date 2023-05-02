package com.example.alarmapp.AlarmList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmapp.databinding.ItemBinding
import com.example.alarmapp.model.Alarm

class AdapterAlarm() :
    RecyclerView.Adapter<AdapterAlarm.AlarmViewHolder>() {

    private var mlist = ArrayList<Alarm>()
    private var onToggleListener: OnToggleListener? = null
    private var onClickAlarmListener: OnClickAlarmListener? = null

    inner class AlarmViewHolder(var binding: ItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, View.OnLongClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        fun bind(alarm: Alarm) {
            binding.textView.text = alarm.getTime()
            binding.textView2.text = alarm.getRepeat()
            binding.switch1.isChecked = alarm.start
            binding.switch1.setOnCheckedChangeListener { btnView, isCheck ->

                alarm.start = isCheck
                onToggleListener?.onToggle(alarm)
            }


        }

        override fun onClick(v: View?) {

            onClickAlarmListener?.onClick(mlist[adapterPosition])
        }

        override fun onLongClick(v: View?): Boolean {
            onClickAlarmListener?.onLongClick(mlist[adapterPosition])

            return true

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(mlist.get(position))

    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setdata(it: List<Alarm>) {
        mlist = it as ArrayList<Alarm>
        notifyDataSetChanged()
    }

    fun addOnToggleListener(onToggleListener: OnToggleListener) {
        this.onToggleListener = onToggleListener
    }

    fun addOnclickAlarmListener(onClickAlarmListener: OnClickAlarmListener) {
        this.onClickAlarmListener = onClickAlarmListener
    }

}