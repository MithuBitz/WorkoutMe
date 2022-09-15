package com.example.workoutme

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutme.databinding.ActivityExerciseBinding
import com.example.workoutme.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding: ActivityExerciseBinding? = null
    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseProgress = 0

    //Create variable for restTimerDuration and exerciseTimerDuration
    private var restTimerDuration: Long = 10
    private var exerciseTimerDuration: Long = 30

    //Create a variable for the TextToSpeech object
    private var tts: TextToSpeech? = null
    //Create a variable for MediaPlayer
    private var player: MediaPlayer? = null

    //Intialize exercise List with the Exercise Model
    private var exerciseList: ArrayList<ExerciseModel>? = null
    //Intialize the current exercise position and hold it to -1 so that when increment its start from 0
    private var currentExercisePostion = -1
    //Initialize the adapter
    private var exerciseAdapter: ExerciseStatusAdapter? = null

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

        //Initialize the TTS
        tts = TextToSpeech(this, this)

        binding?.toolbarExercise?.setNavigationOnClickListener {
            customDialogForBackBtn()
        }

        setRestView()
        setUpExerciseStatusRecyclrView()
    }

    override fun onBackPressed() {
        customDialogForBackBtn()
        //super.onBackPressed()
    }

    //Create a funtion to show the dialog
    private fun customDialogForBackBtn(){
        //Initaialize the custom Dialog
        val customDialog = Dialog(this)
        //Initialize the binding for the custom dialog layout
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        //set the layout for the custom dialog
        customDialog.setContentView(dialogBinding.root)

        //when click on outside of the dialog nothing happen
        customDialog.setCanceledOnTouchOutside(false)
        //When click on yes button close the Exercise Activity and also dismiss the custom dialog
        dialogBinding.yesBtn.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        ///when NO btn is pressed close the dialog
        dialogBinding.noBtn.setOnClickListener {
            customDialog.dismiss()
        }
        //Show the dialog
        customDialog.show()
    }

    //Create a function for Exercise Status Recycler View
    private fun setUpExerciseStatusRecyclrView(){
        //set the LayoutManager for the recyclerView
        binding?.statusRV?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //set the adapter
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        //Set the adapter for the recycler view
        binding?.statusRV?.adapter = exerciseAdapter


    }

    private fun setRestView(){

        //set the MediaPlayer sound here so that when rest view appear it will play
        //Try block
        try {
            //Create a variable for soundUri to parse the music file from the location
            val soundUri = Uri.parse("android.resource://com.example.workoutme/" + R.raw.start)
            //Create the MediaPlayer with application context and uri argument
            player = MediaPlayer.create(applicationContext, soundUri)
            //set the isLooping to false
            player?.isLooping = false
            //set the player to start
            player?.start()
        } catch (e: Exception) {
            //Catch block with printStack
            e.printStackTrace()
        }

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

        speakOut("Take a rest")

        binding?.nextExerciseTV?.text = exerciseList!![currentExercisePostion + 1].getName()

        setRestProgressBar()
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

        //Speak the exercise name
        speakOut(exerciseList!![currentExercisePostion].getName())

        // Set the image of the exercise from the arraylist of exercise
        binding?.imageIV?.setImageResource(exerciseList!![currentExercisePostion].getImage())
        //Set the exercise name taken from the arraylist
        binding?.exerciseTV?.text = exerciseList!![currentExercisePostion].getName()

        setExerciseProgressBar()
    }

    private fun setRestProgressBar() {

        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000) {
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
                //Set the IsSelected to true so that recycler view is working for backgroudn
                exerciseList!![currentExercisePostion].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setExerciseView()

            }
        }.start()

    }

    private fun setExerciseProgressBar(){

        binding?.exerciseProgressBar?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                //binding?.titleTV?.text = "You completed: " + exerciseList!![currentExercisePostion].getName()
                exerciseProgress++
                binding?.exerciseProgressBar?.progress = 30 - exerciseProgress
                binding?.exerciseTimerTV?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {

                // if current exercise position is less than exerciseList size minus 1
                if (currentExercisePostion < exerciseList!!.size - 1) {
                    exerciseList!![currentExercisePostion].setIsSelected(false)
                    exerciseList!![currentExercisePostion].setIsCompleted(true)
                    exerciseAdapter?.notifyDataSetChanged()
                    //set the rest view
                    setRestView()
                } else {
                    //else display a toast for complete the exercise
                    //Toast.makeText(this@ExerciseActivity, "Congrats you completed the exercise", Toast.LENGTH_LONG).show()
                        finish()
                        val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                        startActivity(intent)
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
        if (tts != null){
            tts?.stop()
            tts?.shutdown()
        }
        if (player != null){
            player?.stop()
        }
        binding = null
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "Language not supported")
            }
        } else {
            Log.e("TTS", "TTS service not initialize properly")
        }
    }

    private fun speakOut(text: String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH, null, "")
    }
}