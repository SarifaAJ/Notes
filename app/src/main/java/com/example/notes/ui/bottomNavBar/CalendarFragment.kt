package com.example.notes.ui.bottomNavBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import com.example.notes.databinding.FragmentCalendarBinding
import com.example.notes.ui.calendar.MonthlyFragment
import com.example.notes.ui.calendar.WeeklyFragment

class CalendarFragment : Fragment() {

    private lateinit var binding : FragmentCalendarBinding
    private var isMonthlyFragment : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTemplate.setOnClickListener {
            toggleView()
            updateToggleButton()
        }

        // Tampilkan fragment pertama kali aplikasi dijalankan
        updateCurrentFragment()

        updateToggleButton()
    }

    private fun updateCurrentFragment() {
        val fragment = if (isMonthlyFragment) {
            MonthlyFragment()
        } else {
            WeeklyFragment()
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun updateToggleButton() {
        if (isMonthlyFragment) {
            binding.btnTemplate.text = getString(R.string.month)
        } else {
            binding.btnTemplate.text = getString(R.string.weeks)
        }
    }

    private fun toggleView() {
        isMonthlyFragment = !isMonthlyFragment

        updateCurrentFragment()
    }
}