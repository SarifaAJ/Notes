package com.example.notes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.notes.R
import com.example.notes.databinding.ActivityLoginBinding
import com.example.notes.databinding.ActivitySignUpBinding
import com.example.notes.roomDatabase.helper.DBHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var db : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this)

        binding.btnUser.setOnClickListener {
            val moveIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(moveIntent)
        }
        binding.confirmUser.setOnClickListener {
            val moveIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(moveIntent)
        }

        binding.btnUser.setOnClickListener {
            val usernameText = binding.username.text.toString()
            val passwordText = binding.password.text.toString()

            if (TextUtils.isEmpty(usernameText) || TextUtils.isEmpty(passwordText)) {
                Toast.makeText(this, "Add Username and Password", Toast.LENGTH_SHORT).show()
            } else {
                val checkUser = db.checkUserPass(usernameText, passwordText)
                if (checkUser) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Wrong Username and Password", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}