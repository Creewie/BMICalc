package com.example.bmi_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Iweight = findViewById<EditText>(R.id.weight)
        val Iheight = findViewById<EditText>(R.id.height)
        val checkBMI = findViewById<Button>(R.id.checkBMI)
        val resultText = findViewById<TextView>(R.id.resultText)
        val resultImg = findViewById<ImageView>(R.id.resultImage)

        checkBMI.setOnClickListener{
            val height = Iheight.text.toString().toFloatOrNull() ?: 0f
            val weight = Iweight.text.toString().toFloatOrNull() ?: 0f

            if( height > 0f && weight > 0f){
                val bmi = weight / (height*height)
                val bmiIndx = Math.round(bmi * 100) / 100.00

                if (bmiIndx < 18.5){
                    resultText.text = "Masz niedowagę! (${bmiIndx})"
                    resultImg.setImageResource(R.drawable.underweight)
                }
                else if (bmiIndx in 18.5 .. 24.9){
                    resultText.text = "Masz normalną wagę! (${bmiIndx})"
                    resultImg.setImageResource(R.drawable.normal)
                }
                else if (bmiIndx in 25.0 .. 29.9){
                    resultText.text = "Masz nadwagę! (${bmiIndx})"
                    resultImg.setImageResource(R.drawable.overweight)
                }
                else if (bmiIndx >= 30){
                    resultText.text = "Masz otyłość! (${bmiIndx})"
                    resultImg.setImageResource(R.drawable.obesity)
                }
                else{
                    resultText.text = "Wystąpił błąd! :("
                }
            }
            else{
                resultText.text = "Puste dane! Z czego ja mam liczyć?"
            }
        }
    }
}