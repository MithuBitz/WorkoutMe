package com.example.workoutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workoutme.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    // TODO Create a companion object to hold two constant for the matricUnit view and us matricUnit view
    companion object {
        private const val MATRIC_UNIT_VIEW = "MATRIC_UNIT_VIEW"
        private const val US_MATRIC_UNIT_VIEW = "US_MATRIC_UNIT_VIEW"
    }

    private var binding: ActivityBmiBinding? = null

    //TODO Create a variable to check what unit is selected now
    private var currentViewUnit: String = "MATRIC_UNIT_VIEW"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.bmiToolbar)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }

        binding?.bmiToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        showMatricUnitView()
        //TODO Set the onCheckedChangeListener for the radio buttons group
        binding?.radioGroupBtn?.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.matricUnitRB){
                showMatricUnitView()
            } else {
                showUsMatricUnit()
            }
        }

        // Set onClickListenr for the Calculate Btn
        binding?.btnCalculateUnits?.setOnClickListener {
            bmiCalculation()
        }
    }

    //TODO Create a function to show the EditText field only for matricSystem
    private fun showMatricUnitView(){
        currentViewUnit = MATRIC_UNIT_VIEW

        binding?.matricUnitWgtTIL?.visibility = View.VISIBLE
        binding?.matricUNitHeightTIL?.visibility = View.VISIBLE

        binding?.usMatricUnitWgtTIL?.visibility = View.GONE
        binding?.matricUsUnitFeetTIL?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility =View.GONE

        binding?.matricUnitWeightET?.text!!.clear()
        binding?.matricUnitHeightET?.text!!.clear()

        binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
    }

    //TODO Create a function to show the EditText field only for US matric System

    private fun showUsMatricUnit(){
        currentViewUnit = US_MATRIC_UNIT_VIEW

        binding?.matricUnitWgtTIL?.visibility = View.GONE
        binding?.matricUNitHeightTIL?.visibility = View.GONE

        binding?.usMatricUnitWgtTIL?.visibility = View.VISIBLE
        binding?.matricUsUnitFeetTIL?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility =View.VISIBLE

        binding?.usMatricUnitWghtET?.text!!.clear()
        binding?.usMatricUnitFeetET?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()

        binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
    }


    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding?.matricUnitWeightET?.text.toString().isEmpty()){
            isValid = false
        } else if (binding?.matricUnitHeightET?.text.toString().isEmpty()){
            isValid = false
        }

        return isValid
    }

    private fun validateUsMatricUnit(): Boolean {
        var isValid = true

        if(binding?.usMatricUnitFeetET?.text.toString().isEmpty()){
            isValid = false
        } else if (binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
            isValid = false
        } else if(binding?.usMatricUnitWghtET?.text.toString().isEmpty()){
            isValid = false
        }

        return isValid
    }

    private fun bmiCalculation(){
        if (currentViewUnit == MATRIC_UNIT_VIEW){
            //first check if validateMatricUnit is true or false
            if (validateMetricUnits()) {
                //If true then
                //create a variable to hold the weight and height value which is comeing from the EditText
                val weightValue: Float = binding?.matricUnitWeightET?.text.toString().toFloat()
                val heightValue: Float = binding?.matricUnitHeightET?.text.toString().toFloat() / 100

                val bmiMatric = weightValue / (heightValue * heightValue)
                //else if its false then Show a Toast

                displayBmiResult(bmiMatric)
            }else {
                Toast.makeText(this, "Please Input value", Toast.LENGTH_LONG).show()
            }
        }else {
                if (validateUsMatricUnit()) {
                    val heightFeetValue: Float = binding?.usMatricUnitFeetET?.text.toString().toFloat()
                    val heightInchValue: Float = binding?.etUsMetricUnitHeightInch?.text.toString().toFloat()
                    val usWeightValue: Float = binding?.usMatricUnitWghtET?.text.toString().toFloat()

                    val heightValue = heightFeetValue + heightInchValue * 12

                    val bmiUs = 703 * (usWeightValue / (heightValue*heightValue))

                    displayBmiResult(bmiUs)
                } else {
                    Toast.makeText(this, "Please Input value", Toast.LENGTH_LONG).show()
                }
        }
    }


    private fun displayBmiResult(bmi: Float){

        var bmiLebel: String = ""
        var bmiSummery: String = ""

        if(bmi.compareTo(16f) <= 0){
            bmiLebel = "Severely Underweight"
            bmiSummery = "You must eat otherwise you will be invisible from world"

        } else if (bmi.compareTo(16f) > 0  && bmi.compareTo(18f) <= 0){
            bmiLebel = "Underweight"
            bmiSummery = "You must eat good and healthy food more than today"

        } else if (bmi.compareTo(18f) > 0  && bmi.compareTo(24f) <= 0){
            bmiLebel = "Normal"
            bmiSummery = "Waoo you looks very healthy and fit"

        } else if (bmi.compareTo(24f) > 0  && bmi.compareTo(29f) <= 0){
            bmiLebel = "Overweight"
            bmiSummery = "Take care of your health and burn some fat"

        } else if (bmi.compareTo(29f) > 0  && bmi.compareTo(34f) <= 0){
            bmiLebel = "Moderately Obese"
            bmiSummery = "Hey! Are you trying to explode yourself useing your fat"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDiplayBMIResult?.visibility = View.VISIBLE
        binding?.bmiValueTV?.text = bmiValue
        binding?.bmiMsgTV?.text = bmiLebel
        binding?.bmiSummeryTV?.text = bmiSummery
    }
}