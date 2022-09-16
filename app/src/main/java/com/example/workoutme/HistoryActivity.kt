package com.example.workoutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutme.databinding.ActivityExerciseBinding
import com.example.workoutme.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setSupportActionBar(binding?.historyTB)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "History"
        }
        binding?.historyTB?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}