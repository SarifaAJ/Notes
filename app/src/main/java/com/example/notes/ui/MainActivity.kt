package com.example.notes.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.notes.R
import com.example.notes.adapter.OnBoardingViewPagerAdapter
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.roomDatabase.model.OnBoardingData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var onBoardingViewPager: ViewPager
    private lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private var position = 0
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)

        if (restorePrefData()) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            setPrefData()

            val onBoardingData: MutableList<OnBoardingData> = ArrayList()
            onBoardingData.add(
                OnBoardingData(
                    "Welcome to the Note App",
                    "Start your journey of organized and hassle-free note-taking with our intuitive and feature-rich application.",
                    R.drawable.events
                )
            )
            onBoardingData.add(
                OnBoardingData(
                    "Create and Organize Your Notes",
                    "Effortlessly capture and organize your thoughts, ideas, and important information. With our app, you can create notes, to-do lists, and reminders, making it easy to stay on top of your tasks.",
                    R.drawable.checklist
                )
            )
            onBoardingData.add(
                OnBoardingData(
                    "Get Started",
                    "Dive right into the world of organized note-taking! Sign up or log in to begin your productive journey with our Note App.",
                    R.drawable.team_checklist
                )
            )
            onBoardingViewPager = binding.screenPager
            tabLayout = binding.tabLayout

            setOnBoardingViewPagerAdapter(onBoardingData)

            binding.next.setOnClickListener {
                if (position < onBoardingData.size) {
                    position++
                    onBoardingViewPager.currentItem = position
                }
                if (position == onBoardingData.size) {
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                }
            }

            tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    position = tab?.position ?: 0
                    if (tab?.position == onBoardingData.size - 1) {
                        binding.next.text = "Get Started"
                    } else {
                        binding.next.text = "Next"
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    // Do nothing
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // Do nothing
                }
            })
        }
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>) {
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager.adapter = onBoardingViewPagerAdapter
        tabLayout.setupWithViewPager(onBoardingViewPager)
    }

    private fun setPrefData() {
        sharedPreferences.edit()
            .putBoolean("isFirstTimeRun", true)
            .apply()
    }

    private fun restorePrefData(): Boolean {
        return sharedPreferences.getBoolean("isFirstTimeRun", false)
    }
}
