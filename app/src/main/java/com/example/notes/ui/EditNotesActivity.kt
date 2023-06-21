package com.example.notes.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.notes.R
import com.example.notes.databinding.ActivityEditNotesBinding
import com.example.notes.roomDatabase.MyApp.Companion.db
import com.example.notes.roomDatabase.model.NotesModel
import java.util.Calendar

class EditNotesActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding : ActivityEditNotesBinding

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back button
        binding.btnBackArrow.setOnClickListener {
            finish()
        }

        //menerima data yang di kirimkan dengan Intent ketika berpindah Activity
        val data = intent.getParcelableExtra("data")?: NotesModel()
        Log.e("data", "${data.description}.")

        //memasukan data produk ke edittext untuk di perbarui
        binding.tvTitle.setText(data.title)
        binding.tvDesc.setText(data.description)
        binding.tvDate.setText(data.date)

        binding.btnReminder.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }
        binding.FABDone.setOnClickListener {
            data.title = binding.tvTitle.text.toString()
            data.description = binding.tvDesc.text.toString()
            data.date = binding.tvDate.text.toString()

            db?.notesDao()?.updateNotes(data)

            Toast.makeText(this@EditNotesActivity, "Notes berhasil diperbarui", Toast.LENGTH_SHORT).show()

            this@EditNotesActivity.finish()
        }
        binding.btnDelete.setOnClickListener {
            db?.notesDao()?.deleteNotes(data)

            Toast.makeText(this@EditNotesActivity, "Notes berhasil dihapus", Toast.LENGTH_SHORT).show()

            this@EditNotesActivity.finish()
        }
    }
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