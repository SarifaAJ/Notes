package com.example.notes.calendarAdapter.holder

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.calendarAdapter.CalendarAdapter
import com.example.notes.calendarAdapter.CalendarUtils
import com.example.notes.databinding.CalendarCellBinding
import java.time.LocalDate

class CalendarHolder(
    private val binding: CalendarCellBinding,
    private val itemClickListener: CalendarAdapter.OnItemClickListener,
    private val days: List<LocalDate?>) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private val calendarUtils = CalendarUtils().apply {
        selectedDate = LocalDate.now()
    }

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(date: LocalDate?) {
        if (date == null) {
            binding.tvCellDay.text = ""
            binding.parentView.visibility = View.INVISIBLE
        } else {
            binding.tvCellDay.text = date.dayOfMonth.toString()
            binding.parentView.visibility = View.VISIBLE

            val isSelected = date == calendarUtils.selectedDate
            val context = binding.root.context

            if (isSelected) {
                binding.parentView.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.light_blue)
                )
            } else {
                binding.parentView.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    override fun onClick(view: View) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            val date = days[position]
            itemClickListener.onItemClick(position, date)
        }
    }
}