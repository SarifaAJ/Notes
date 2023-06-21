package com.example.notes.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.notes.R
import com.example.notes.databinding.ActivityAddNotesBinding
import com.example.notes.roomDatabase.MyApp.Companion.db
import com.example.notes.roomDatabase.model.NotesModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddNotesActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityAddNotesBinding

    // date and time
    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0

    var savedYear = 0
    var savedMonth = 0
    var savedDay = 0
    var savedHour = 0
    var savedMinute = 0


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back button
        binding.btnBackArrow.setOnClickListener {
            finish()
        }

        // date and time setting
        pickDate()

        //save button
        binding.FABDone.setOnClickListener {
            val data = NotesModel()
            data.title = binding.tvTitle.text.toString()
            data.description = binding.tvDesc.text.toString()
            data.date = getCurrentDateTime()

            db?.notesDao()?.insertNotes(data)

            Toast.makeText(this@AddNotesActivity, "Note berhasil dibuat", Toast.LENGTH_SHORT).show()

            this@AddNotesActivity.finish()
        }

    }

    fun getCurrentDateTime():String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id"))
        return sdf.format(Date())
    }

    // back button setting
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    // date and time setting
    private fun getDateTimeCalendar() {
        val calendar : Calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
    }
    private fun pickDate() {
        binding.btnReminder.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }
    // date
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(this, this, hour, minute, true).show()
    }
    // time
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        binding.tvDate.text = "$savedDay-$savedMonth-$savedYear   $savedHour:$savedMinute"
    }
}