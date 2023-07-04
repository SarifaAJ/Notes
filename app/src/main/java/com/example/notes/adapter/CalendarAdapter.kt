package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.adapter.holder.CalendarHolder
import com.example.notes.databinding.CalendarCellBinding
import java.time.LocalDate

class CalendarAdapter(private val days: ArrayList<LocalDate?>, private val calendarUtils: CalendarUtils) : RecyclerView.Adapter<CalendarHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, date: LocalDate?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CalendarCellBinding.inflate(inflater, parent, false)
        val layoutParams = binding.root.layoutParams

        if (days.size > 15) {
            layoutParams.height = (parent.height * 0.1666).toInt()
        } else {
            layoutParams.height = parent.height
        }

        return CalendarHolder(binding, itemClickListener!!, days.toList())
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
        val date = days[position]
        if (date != null) {
            holder.bind(date, calendarUtils)
        }
    }
}