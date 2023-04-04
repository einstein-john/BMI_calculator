package com.example.bmicalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //TODO set height unit to meters
        binding.heightUnit.text = ".m"

        binding.CVResult.visibility = View.INVISIBLE



    }
    private fun calculateBMI(weight:Double, height:Double):Double{
        return (weight/(height*height))
    }

    private fun showResult(status:String){
        binding.txtWeightStatus.text  = status
        binding.CVResult.visibility = View.VISIBLE

        when (status) {
            "Underweight" -> binding.txtWeightStatus.setTextColor(Color.parseColor("#ffd966"))
            "Normal" -> binding.txtWeightStatus.setTextColor(Color.parseColor("#3d7c4d"))
            "Overweight" -> binding.txtWeightStatus.setTextColor(Color.parseColor("#980804"))
            else -> binding.txtWeightStatus.setTextColor(Color.parseColor("#ffffff"))
        }

    }



    fun setStatus(view: View) {
        binding.CVResult.visibility = View.INVISIBLE

        val height = binding.txtHeight.text.toString()
        val weight = binding.txtWeight.text.toString()

        if (height == "" && weight == ""){
            Toast.makeText(this, "Weight and height cannot be left empty",Toast.LENGTH_LONG).show()
        }else if( height.toDouble() > 8.0){
            Toast.makeText(this, "Damm bruv u ain't that tall",Toast.LENGTH_LONG).show()
        }else{

            val bmi =  calculateBMI(weight.toDouble(), height.toDouble())
            val twoDpBmi = String.format("%.2f",bmi)
            binding.txtResult.text = "$twoDpBmi kg/m^2"

            if (bmi < 18.4){
                showResult("Underweight")
            }else if(bmi <  24.9){
                showResult("Normal")

            }else {
                showResult("Overweight")

            }
        }
        binding.txtWeight.text.clear()
        binding.txtHeight.text.clear()
        
        //TODO drop the keyboard when btnCalculate is clicked
        // Get a reference to the InputMethodManager
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

// Check if the keyboard is currently shown
        if (imm.isAcceptingText) {
            // Hide the keyboard
            imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
