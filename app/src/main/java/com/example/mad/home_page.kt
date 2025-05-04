package com.example.mad

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random

class home_page : AppCompatActivity() {
    private lateinit var mantraTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var click1Layout: LinearLayout
    private lateinit var click4Layout: LinearLayout
    private lateinit var click3Layout: LinearLayout
    private lateinit var click2Layout: LinearLayout

    private val mantras = arrayOf(
        "Believe in yourself and all that you are.",
        "Stay positive, work hard, make it happen.",
        "Success is not final, failure is not fatal.",
        "Small steps every day lead to big results.",
        "Your only limit is your mind."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        mantraTextView = findViewById(R.id.mantraTextView)
        progressBar = findViewById(R.id.progressBar)
        val mantraBox: LinearLayout = findViewById(R.id.badge)
        click1Layout = findViewById(R.id.task_button)
        click3Layout = findViewById(R.id.click3)
        click2Layout = findViewById(R.id.click2)
        click4Layout = findViewById(R.id.click4) // Initialize the profile LinearLayout

        progressBar.max = 100
        progressBar.progress = 60

        updateMantra()

        mantraBox.setOnClickListener {
            updateMantra()
        }

        // Task button click
        click1Layout.setOnClickListener {
            val intent = Intent(this, Task::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }


        click4Layout.setOnClickListener {
            val profileIntent = Intent(this,profile::class.java)
            startActivity(profileIntent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        click2Layout.setOnClickListener {
            val profileIntent = Intent(this,calender_view::class.java)
            startActivity(profileIntent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }


        // Profile button click
        click3Layout.setOnClickListener {
            val profileIntent = Intent(this,collabration::class.java)
            startActivity(profileIntent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        // Your existing bottom navigation code...
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navView.selectedItemId = R.id.nav_home
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    true
                }
                R.id.nav_tasks -> {
                    startActivity(Intent(this,Task::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                    true
                } // Already on tasks page
                R.id.nav_profile -> {
                    startActivity(Intent(this, profile::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                    true
                }
                else -> false
            }
        }
    }


    private fun updateMantra() {
        val index = Random.nextInt(mantras.size)
        mantraTextView.text = mantras[index]
    }


}