package com.example.workoutme

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutme.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExerciseBinding? = null
    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseProgress = 0

    //Intialize exercise List with the Exercise Model
    private var exerciseList: ArrayList<ExerciseModel>? = null
    //Intialize the current exercise position and hold it to -1 so that when increment its start from 0
    private var currentExercisePostion = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // set the Exercise arrayList Constant object exercise list function
        exerciseList = Constants.defaultExercise()

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setRestView()
    }

    private fun setRestView(){

        binding?.titleTV?.visibility = View.VISIBLE
        binding?.progressBarFL?.visibility = View.VISIBLE
        binding?.exerciseViewFL?.visibility = View.INVISIBLE
        binding?.exerciseTV?.visibility = View.INVISIBLE
        binding?.imageIV?.visibility = View.INVISIBLE
        binding?.simpleTV?.visibility = View.VISIBLE
        binding?.nextExerciseTV?.visibility = View.VISIBLE

        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        binding?.nextExerciseTV?.text = exerciseList!![currentExercisePostion + 1].getName()

        setProgressBar()
    }

    private fun setExerciseView() {
        binding?.titleTV?.visibility = View.INVISIBLE
        binding?.progressBarFL?.visibility = View.INVISIBLE
        binding?.exerciseViewFL?.visibility = View.VISIBLE
        binding?.exerciseTV?.visibility = View.VISIBLE
        binding?.imageIV?.visibility = View.VISIBLE
        binding?.simpleTV?.visibility = View.INVISIBLE
        binding?.nextExerciseTV?.visibility = View.INVISIBLE


        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        // Set the image of the exercise from the arraylist of exercise
        binding?.imageIV?.setImageResource(exerciseList!![currentExercisePostion].getImage())
        //Set the exercise name taken from the arraylist
        binding?.exerciseTV?.text = exerciseList!![currentExercisePostion].getName()

        setExerciseProgressBar()
    }

    private fun setProgressBar() {

        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //increment the restProgress by one in every tick of 1000 Millis
                restProgress++
                //set the progress bar progress to totalProgress - restProgress
                binding?.progressBar?.progress = 10 - restProgress
                //set the timerText to totalProgress - restProgress
                binding?.timerTV?.text = (10 - restProgress).toString()



            }

            override fun onFinish() {
                //increment the current Exercise Transliterator.Position global variable
                currentExercisePostion++

                setExerciseView()

            }
        }.start()

    }

    private fun setExerciseProgressBar(){

        binding?.exerciseProgressBar?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                //binding?.titleTV?.text = "You completed: " + exerciseList!![currentExercisePostion].getName()
                exerciseProgress++
                binding?.exerciseProgressBar?.progress = 30 - exerciseProgress
                binding?.exerciseTimerTV?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                // if current exercise position is less than exerciseList size minus 1
                if (currentExercisePostion < exerciseList!!.size - 1) {
                    //set the rest view
                    setRestView()
                } else {
                    //else display a toast for complete the exercise
                    Toast.makeText(this@ExerciseActivity, "Congrats you completed the exercise", Toast.LENGTH_LONG).show()
                }
            }

        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()

        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding = null
    }
}