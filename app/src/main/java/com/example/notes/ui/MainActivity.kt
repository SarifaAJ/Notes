package com.example.notes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread.sleep(2000)
        installSplashScreen()

        binding.btnLanjutkan.setOnClickListener {
            val moveIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(moveIntent)
        }
    }
}



