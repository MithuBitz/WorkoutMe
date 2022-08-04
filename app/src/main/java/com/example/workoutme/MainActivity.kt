package com.example.workoutme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.workoutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Create a variable for finding with respactive Activity Binding
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflate the ActivityMainBinding into binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        //set the content view with the root of the binding layout
        setContentView(binding?.root)

        binding?.startFL?.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}