package com.example.notes.ui.calendar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.adapter.CalendarAdapter
import com.example.notes.adapter.CalendarUtils
import com.example.notes.databinding.FragmentWeeklyBinding
import com.example.notes.ui.AddNotesActivity
import java.time.LocalDate

class WeeklyFragment : Fragment(), CalendarAdapter.OnItemClickListener {

    private var weeklyBinding: FragmentWeeklyBinding? = null
    private val binding get() = weeklyBinding!!

    private var notesListFragment: NotesListFragment? = null
    private var selectedDate: LocalDate? = null

    // calendar setting
    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView

    private val calendarUtils = CalendarUtils().apply {
        selectedDate = LocalDate.now()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weeklyBinding = FragmentWeeklyBinding.inflate(inflater, container, false)
        val view = binding.root

        // calendar setting
        monthYearText = binding.tvMonth
        calendarRecyclerView = binding.calendarRecyclerView

        //add note button
        binding.FABAdd.setOnClickListener {
            val moveIntent = Intent(activity, AddNotesActivity::class.java)
            startActivity(moveIntent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // calendar setting
        setWeekView()

        val previousButton: ImageButton = binding.previousButton
        previousButton.setOnClickListener { previousWeekAction(it) }

        val nextButton: ImageButton = binding.nextButton
        nextButton.setOnClickListener { nextWeekAction(it) }

        // Create instance of NotesListFragment
        notesListFragment = NotesListFragment.newInstance(selectedDate)

        // Replace the fragment container with NotesListFragment
        childFragmentManager.beginTransaction()
            .replace(R.id.main_recy_data, notesListFragment!!)
            .commit()

    }

    // calendar setting
    private fun setWeekView() {
        monthYearText.text = calendarUtils.monthYearFromDate(calendarUtils.selectedDate)
        val daysInWeek = calendarUtils.daysInWeekArray(calendarUtils.selectedDate)
        val calendarAdapter = CalendarAdapter(daysInWeek, calendarUtils)
        calendarAdapter.setOnItemClickListener(this)
        val layoutManager = GridLayoutManager(requireContext(), 7)
        binding.calendarRecyclerView.layoutManager = layoutManager
        binding.calendarRecyclerView.adapter = calendarAdapter

    }

    // calendar setting
    private fun previousWeekAction(view: View) {
        calendarUtils.selectedDate = calendarUtils.selectedDate.minusWeeks(1)
        setWeekView()
    }

    // calendar setting
    private fun nextWeekAction(view: View) {
        calendarUtils.selectedDate = calendarUtils.selectedDate.plusWeeks(1)
        setWeekView()
    }

    // calendar setting
    override fun onItemClick(position: Int, date: LocalDate?) {
        calendarUtils.selectedDate = date?: LocalDate.now()
        setWeekView()

        notesListFragment?.updateSelectedDate(selectedDate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        weeklyBinding = null
    }
}
