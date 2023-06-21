package com.example.notes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.R
import com.example.notes.ui.bottomNavBar.CalendarFragment
import com.example.notes.ui.bottomNavBar.HomeFragment
import com.example.notes.ui.bottomNavBar.OtherFragment
import com.example.notes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.notes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.calendar -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CalendarFragment())
                        .commit()
                    true
                }
                R.id.other -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, OtherFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // Tampilkan fragment pertama kali aplikasi dijalankan
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
    }
}