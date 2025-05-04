package com.example.mad

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class collabration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_collabration)

        // Initialize FAB
        val fabAddTask = findViewById<FloatingActionButton>(R.id.add_task_button)
        fabAddTask.setOnClickListener {
            // Navigate to AddTaskActivity
            startActivity(Intent(this, add_task::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        // Initialize BottomNavigationView
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Clear any existing selection
        navView.menu.setGroupCheckable(0, true, false) // Temporarily disable checking
        navView.selectedItemId = View.NO_ID
        navView.menu.setGroupCheckable(0, true, true) // Re-enable checking

        // Set up navigation item selection listener
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, home_page::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                    true
                }
                R.id.nav_tasks -> {
                    // Start Task activity if needed
                    startActivity(Intent(this, Task::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                    true
                }
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
}