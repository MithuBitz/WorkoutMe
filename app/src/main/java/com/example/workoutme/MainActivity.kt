package com.example.workoutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flStartBtn: FrameLayout = findViewById(R.id.startFL)
        flStartBtn.setOnClickListener {
            Toast.makeText(this, "Start the Workout", Toast.LENGTH_LONG).show()
        }
    }
}