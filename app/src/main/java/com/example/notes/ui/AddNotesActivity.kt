package com.example.notes.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.notes.R
import com.example.notes.databinding.ActivityAddNotesBinding
import com.example.notes.databinding.BottomSheetLayoutBinding
import com.example.notes.roomDatabase.MyApp.Companion.db
import com.example.notes.roomDatabase.model.NotesModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding

    private var currentColor: Int = 0

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back button
        binding.btnBackArrow.setOnClickListener {
            finish()
        }

        // color button
        binding.btnColor.setOnClickListener {
            showDialog()
        }

        // save button
        binding.FABDone.setOnClickListener {
            val data = NotesModel()
            data.title = binding.tvTitle.text.toString()
            data.description = binding.tvDesc.text.toString()
            data.date = getCurrentDateTime()
            data.color = currentColor

            db?.notesDao()?.insertNotes(data)

            Toast.makeText(this@AddNotesActivity, "Note berhasil dibuat", Toast.LENGTH_SHORT).show()

            this@AddNotesActivity.finish()
        }

    }

    // bottom sheet dialog
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = BottomSheetLayoutBinding.inflate(dialog.layoutInflater)
        dialog.setContentView(binding.root)

        binding.red.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.pastel_red)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.orange.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.pastel_orange)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.yellow.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.pastel_yellow)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.green.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.pastel_green)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.cyan.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.pastel_cyan)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.blue.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.pastel_blue)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.purple.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.pastel_purple)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.pink.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.pastel_pink)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.white.setOnClickListener {
            val color = ContextCompat.getColor(this@AddNotesActivity, R.color.white)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

    }

    fun changeLayoutColor(color: Int) {
        binding.root.setBackgroundColor(color)
    }

    // mengambil data waktu secara real-time
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
}