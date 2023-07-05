package com.example.notes.ui.bottomNavBar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.example.notes.R
import com.example.notes.databinding.FragmentOtherBinding
import com.example.notes.roomDatabase.helper.DBHelper
import com.example.notes.ui.otherFragment.PrivacyPoliceActivity
import java.util.Locale

class OtherFragment : Fragment() {

    private var otherBinding: FragmentOtherBinding? = null
    private val binding get() = otherBinding!!

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var modeSwitch: SwitchCompat
    private var isNightMode: Boolean = false
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var db : DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        otherBinding = FragmentOtherBinding.inflate(inflater, container, false)
        val view = binding.root

        db = DBHelper(requireContext())


        // To PrivacyPoliceActivity
        binding.kebijakanPrivasi.setOnClickListener {
            val moveIntent = Intent(activity, PrivacyPoliceActivity::class.java)
            startActivity(moveIntent)
        }

        // Pilihan bahasa
        binding.bahasa.setOnClickListener {
            showConfirmationDialog(view)
        }

        sharedPreferences =
            requireActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE)
        isNightMode = sharedPreferences.getBoolean("night", true)

        modeSwitch = binding.switchMode
        modeSwitch.isChecked = isNightMode

        modeSwitch.setOnCheckedChangeListener { _, isChecked ->
            editor = sharedPreferences.edit()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night", true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night", false)
            }
            editor.apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        otherBinding = null
    }

    // Pilihan bahasa
    private fun showConfirmationDialog(view: View) {
        val languages = resources.getStringArray(R.array.bahasa)
        val selectedItemIndex = sharedPreferences.getInt("selected_language_index", 0)

        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Pilih Bahasa")
        builder.setSingleChoiceItems(languages, selectedItemIndex) { dialog, which ->
            // Handle pilihan bahasa yang dipilih
            val selectedLanguage = languages[which]
            Toast.makeText(view.context, "Bahasa terpilih: $selectedLanguage", Toast.LENGTH_SHORT)
                .show()

            // Simpan indeks bahasa yang dipilih ke SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putInt("selected_language_index", which)
            editor.apply()

            // Terapkan perubahan bahasa
            setAppLanguage(selectedLanguage)

            dialog.dismiss()
        }

        builder.setPositiveButton("OK", null)
        builder.setNegativeButton("Batal", null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun setAppLanguage(language: String?) {
        val locale = when (language) {
            "English" -> Locale.ENGLISH
            "Italiano" -> Locale.ITALIAN
            "Francais" -> Locale.FRENCH
            "日本語" -> Locale.JAPANESE
            else -> Locale("id") // Bahasa Indonesia sebagai default
        }

        Locale.setDefault(locale)

        val configuration = Configuration()
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        requireActivity().recreate()
    }
}