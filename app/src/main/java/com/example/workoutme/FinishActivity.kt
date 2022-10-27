package com.example.workoutme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.workoutme.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.finishActivityToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.finishActivityToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.finishBtn?.setOnClickListener {
            finish()
        }

        val historyDao = (application as WorkoutApp).db.historyDao()
        addDateToDatabase(historyDao)
    }


    private fun addDateToDatabase(historyDao: HistoryDao){
        //This is how get the time
        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.i("DateTime: ", ""+dateTime)
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.i("Formatted Date: ", ""+date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date = date))
            Toast.makeText(applicationContext, "Date is store", Toast.LENGTH_LONG).show()
        }
    }
}