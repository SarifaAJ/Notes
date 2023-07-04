package com.example.notes.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.notes.R
import com.example.notes.databinding.ActivityEditNotesBinding
import com.example.notes.databinding.BottomSheetLayoutBinding
import com.example.notes.roomDatabase.MyApp.Companion.db
import com.example.notes.roomDatabase.model.NotesModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditNotesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditNotesBinding

    private var currentColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back button
        binding.btnBackArrow.setOnClickListener {
            finish()
        }

        // color button
        binding.btnColor.setOnClickListener {
            showDialog()
        }

        //menerima data yang di kirimkan dengan Intent ketika berpindah Activity
        val data = intent.getParcelableExtra("data")?: NotesModel()
        Log.e("data", "${data.description}.")

        //memasukan data produk ke edittext untuk di perbarui
        binding.tvTitle.setText(data.title)
        binding.tvDesc.setText(data.description)
        binding.tvDate.setText(data.date)
        binding.root.setBackgroundColor(data.color )

        // menyimpan data yang sudah diubah
        binding.FABDone.setOnClickListener {
            data.title = binding.tvTitle.text.toString()
            data.description = binding.tvDesc.text.toString()
            data.date = getCurrentDateTime()
            data.color = currentColor

            db?.notesDao()?.updateNotes(data)

            Toast.makeText(this@EditNotesActivity, getString(R.string.notes_diperbarui), Toast.LENGTH_SHORT).show()

            this@EditNotesActivity.finish()
        }

        // membuang data
        binding.btnDelete.setOnClickListener {

            val artDialogBuilder = AlertDialog.Builder (this@EditNotesActivity)

            artDialogBuilder.setTitle(getString(R.string.hapus))
            artDialogBuilder.setMessage(getString(R.string.hapus_desc))
            artDialogBuilder.setCancelable(false)
            artDialogBuilder.setPositiveButton(getString(R.string.hapus)) {_ , _ ->
                db?.notesDao()?.deleteNotes(data)

                Toast.makeText(this@EditNotesActivity, getString(R.string.notes_dihapus), Toast.LENGTH_SHORT).show()

                this@EditNotesActivity.finish()
            }
            artDialogBuilder.setNegativeButton(getString(R.string.tidak)) {_ , _ ->
                Toast.makeText(this@EditNotesActivity, getString(R.string.notes_batal_dihapus), Toast.LENGTH_LONG).show()
            }
            val alertDialogBox = artDialogBuilder.create()
            alertDialogBox.show()
        }
    }

    // bottom sheet dialog
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = BottomSheetLayoutBinding.inflate(dialog.layoutInflater)
        dialog.setContentView(binding.root)

        binding.red.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.pastel_red)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.orange.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.pastel_orange)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.yellow.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.pastel_yellow)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.green.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.pastel_green)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.cyan.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.pastel_cyan)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.blue.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.pastel_blue)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.purple.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.pastel_purple)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.pink.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.pastel_pink)
            changeLayoutColor(color)
            currentColor = color
            dialog.dismiss()
        }
        binding.white.setOnClickListener {
            val color = ContextCompat.getColor(this@EditNotesActivity, R.color.white)
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