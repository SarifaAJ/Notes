package com.example.notes.adapter

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

class CalendarUtils {

    lateinit var selectedDate: LocalDate

    fun daysInMonthArray(date: LocalDate): ArrayList<LocalDate?> {
        val daysInMonthArray = ArrayList<LocalDate?>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = date.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 0..41) {
            val index = i - dayOfWeek
            if (i < dayOfWeek || index >= daysInMonth) {
                daysInMonthArray.add(null)
            } else {
                val dayOfMonth = index + 1
                daysInMonthArray.add(LocalDate.of(date.year, date.month, dayOfMonth))
            }
        }
        return daysInMonthArray
    }

    fun daysInWeekArray(selectedDate: LocalDate): ArrayList<LocalDate?> {
        val days = ArrayList<LocalDate?>()
        val startDate = selectedDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val endDate = startDate.plusWeeks(1)

        var current = startDate
        while (current.isBefore(endDate)) {
            days.add(current)
            current = current.plusDays(1)
        }
        return days
    }

    fun monthYearFromDate(date: LocalDate): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }
}