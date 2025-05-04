package com.example.mad

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)


            val loginTextView = findViewById<TextView>(R.id.login)
             val submit=findViewById<ImageView>(R.id.submit)

            // Navigate to MainActivity2 when loginTextView is clicked
            loginTextView.setOnClickListener {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }

        submit.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }
        }
    }
