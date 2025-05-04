package com.example.mad

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class calender_view : AppCompatActivity() {

    private lateinit var txtMonthYear: TextView
    private lateinit var calendarGrid: GridLayout
    private lateinit var navView: BottomNavigationView
    private var currentDate = Calendar.getInstance()
    private var selectedDayView: TextView? = null
    private val darkPurple = Color.parseColor("#6A0DAD")
    private val lightPurple = Color.parseColor("#E6E6FA")
    private val white = Color.WHITE
    private val black = Color.BLACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender_view)

        // Initialize views
        txtMonthYear = findViewById(R.id.txtMonthYear)
        calendarGrid = findViewById(R.id.calendarGrid)
        navView = findViewById(R.id.bottom_navigation)

        // Set up calendar
        findViewById<Button>(R.id.btnPrev).setOnClickListener { updateMonth(-1) }
        findViewById<Button>(R.id.btnNext).setOnClickListener { updateMonth(1) }
        updateCalendar()

        // Set up bottom navigation
        setupBottomNavigation()
    }

    private fun updateMonth(amount: Int) {
        currentDate.add(Calendar.MONTH, amount)
        selectedDayView = null
        updateCalendar()
    }

    private fun updateCalendar() {
        // Update month/year header
        txtMonthYear.text = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            .format(currentDate.time)

        // Clear previous calendar cells
        calendarGrid.removeAllViews()
        calendarGrid.columnCount = 7

        // Configure calendar parameters
        val calendar = currentDate.clone() as Calendar
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 // Sunday=0
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Add empty cells for days before 1st of month
        for (i in 0 until firstDayOfWeek) {
            addEmptyCell()
        }

        // Add cells for each day of month
        for (day in 1..daysInMonth) {
            addDayCell(day.toString())
        }

        // Fill remaining cells if needed
        val remainingCells = 42 - (firstDayOfWeek + daysInMonth)
        for (i in 0 until remainingCells) {
            addEmptyCell()
        }
    }

    private fun addEmptyCell() {
        val cell = TextView(this).apply {
            text = ""
            setTextColor(lightPurple)
        }
        addCellToGrid(cell)
    }

    private fun addDayCell(day: String) {
        val cell = TextView(this).apply {
            text = day
            gravity = Gravity.CENTER
            setPadding(8, 16, 8, 16)
            textSize = 14f
            setTextColor(black)

            // Create and set default background
            background = createCellBackground(false)

            setOnClickListener {
                handleDaySelection(this, day)
            }
        }
        addCellToGrid(cell)
    }

    private fun addCellToGrid(cell: TextView) {
        val params = GridLayout.LayoutParams().apply {
            width = 0
            height = GridLayout.LayoutParams.WRAP_CONTENT
            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            setMargins(2, 2, 2, 2)
        }
        calendarGrid.addView(cell, params)
    }

    private fun createCellBackground(selected: Boolean): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 8f
            if (selected) {
                setColor(darkPurple)
            } else {
                setColor(Color.TRANSPARENT)
                setStroke(1, lightPurple)
            }
        }
    }

    private fun handleDaySelection(selectedView: TextView, day: String) {
        // Reset previous selection
        selectedDayView?.let {
            it.setTextColor(black)
            it.background = createCellBackground(false)
        }

        // Set new selection
        selectedView.setTextColor(white)
        selectedView.background = createCellBackground(true)
        selectedDayView = selectedView

        // Launch Task activity
        val selectedDate = Calendar.getInstance().apply {
            set(currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                day.toInt())
        }.time

        val intent = Intent(this, Task::class.java).apply {
            putExtra("SELECTED_DATE", SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(selectedDate))
        }
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun setupBottomNavigation() {
        // Clear any existing selection
        navView.menu.setGroupCheckable(0, true, false) // Temporarily disable checking
        navView.selectedItemId = View.NO_ID
        navView.menu.setGroupCheckable(0, true, true) // Re-enable checking

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, home_page::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                    true
                }
                R.id.nav_tasks -> {
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