package com.example.morninghelper.ui.dashboard_activity.fragments.alarm_clock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.morninghelper.R
import com.example.morninghelper.room.alarms.Alarms
import kotlinx.android.synthetic.main.alarm_clock_fragment.view.*
import kotlinx.android.synthetic.main.alarms_recyclerview_layout.view.*


class AlarmRecyclerViewAdapter(
    private val alarm: MutableList<Alarms>,
    private val alarmInterface: AlarmInterface
) : RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        fun onBind() {
            val model = alarm[adapterPosition]
            itemView.alarmTimeTextView.text = model.alarmTime
            itemView.alarmRepeatTextView.text = model.repeat
            itemView.alarmLabelTextView.text = model.label
            itemView.setOnClickListener(this)
            itemView.alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    Toast.makeText(itemView.context, "Alarm is Set from..", Toast.LENGTH_SHORT).show()
                    changeAlarmBackground(R.color.yellowColor)
                }
                else{
                    Toast.makeText(itemView.context, "Alarm is Turned Off", Toast.LENGTH_SHORT).show()
                    changeAlarmBackground(R.color.lightCreamColor)
                }

            }
            if (model.switchOn == 1)
                itemView.alarmSwitch.isChecked = true

        }


        private fun changeAlarmBackground(color: Int) {
            itemView.alarmLayout.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    color
                )
            )
        }


        override fun onClick(v: View?) {
            alarmInterface.onClick(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.alarms_recyclerview_layout, parent, false)
        )
    }

    override fun getItemCount() = alarm.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }


}