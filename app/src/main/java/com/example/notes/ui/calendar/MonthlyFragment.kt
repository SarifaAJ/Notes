package com.example.notes.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.calendarAdapter.CalendarAdapter
import com.example.notes.calendarAdapter.CalendarUtils
import com.example.notes.databinding.FragmentMonthlyBinding
import java.time.LocalDate

class MonthlyFragment : Fragment(), CalendarAdapter.OnItemClickListener {

    private var monthlyBinding: FragmentMonthlyBinding? = null
    private val binding get() = monthlyBinding!!

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView

    private val calendarUtils = CalendarUtils().apply {
        selectedDate = LocalDate.now()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        monthlyBinding = FragmentMonthlyBinding.inflate(inflater, container, false)
        val view = binding.root

        monthYearText = binding.tvMonth
        calendarRecyclerView = binding.calendarRecyclerView

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarUtils.selectedDate = LocalDate.now()

        setMonthView()

        val previousButton: ImageButton = binding.previousButton
        previousButton.setOnClickListener { previousMonthAction(it) }

        val nextButton: ImageButton = binding.nextButton
        nextButton.setOnClickListener { nextMonthAction(it) }
    }

    private fun setMonthView() {
        monthYearText.text = calendarUtils.monthYearFromDate(calendarUtils.selectedDate)
        val daysInMonth = calendarUtils.daysInMonthArray(calendarUtils.selectedDate)
        val calendarAdapter = CalendarAdapter(daysInMonth)
        calendarAdapter.setOnItemClickListener(this)
        val layoutManager = GridLayoutManager(requireContext(), 7)
        binding.calendarRecyclerView.layoutManager = layoutManager
        binding.calendarRecyclerView.adapter = calendarAdapter
    }

    private fun previousMonthAction(view: View) {
        calendarUtils.selectedDate = calendarUtils.selectedDate.minusMonths(1)
        setMonthView()
    }

    private fun nextMonthAction(view: View) {
        calendarUtils.selectedDate = calendarUtils.selectedDate.plusMonths(1)
        setMonthView()
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        if (date !== null) {
            calendarUtils.selectedDate = date
            setMonthView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        monthlyBinding = null
    }
}