package com.example.notes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.notes.R
import com.example.notes.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUser.setOnClickListener {
            val moveIntent = Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(moveIntent)
        }
        binding.confirmUser.setOnClickListener {
            val moveIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(moveIntent)
        }
    }
}