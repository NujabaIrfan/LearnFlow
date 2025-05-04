package com.example.mad

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handling window insets for edge-to-edge support
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Use the main looper explicitly to avoid deprecated behavior
        val handler = Handler(Looper.getMainLooper())

        // Use the handler to post the delayed intent
        handler.postDelayed({
            // Create an Intent to start the Onboarding activity
            val intent = Intent(this,onboardingScreen1::class.java)
            startActivity(intent)
            finish()  // Close the MainActivity so the user can't go back
        }, 3000) // 3 seconds delay before transitioning
    }
}
