package com.example.notes.ui.otherFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.databinding.ActivityArchieveBinding

class ArchieveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArchieveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArchieveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackArrow.setOnClickListener {
            finish()
        }

    }
}