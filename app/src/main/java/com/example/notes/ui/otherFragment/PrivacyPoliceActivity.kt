package com.example.notes.ui.otherFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.R
import com.example.notes.databinding.ActivityPrivacyPoliceBinding

class PrivacyPoliceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPrivacyPoliceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPoliceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackArrow.setOnClickListener {
            finish()
        }

    }
}