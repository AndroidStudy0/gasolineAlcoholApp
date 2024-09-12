package com.isadoralopes.gasolinaalcoolapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcohol: TextInputLayout
    private lateinit var editAlcohol: TextInputEditText

    private lateinit var textInputGasoline: TextInputLayout
    private lateinit var editGasoline: TextInputEditText

    private lateinit var btnCalculate: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iniateComponentsInterface()
        btnCalculate.setOnClickListener {
            calculateBestPrice()
        }
    }

    private fun calculateBestPrice() {
        val priceAlcohol = editAlcohol.text.toString()
        val priceGasoline = editGasoline.text.toString()

        val validationResult = validateFields(priceAlcohol, priceGasoline)

        if (validationResult) {
            val result = priceAlcohol.toDouble() / priceGasoline.toDouble()

            if (result >= 0.7) {
                textResult.text = "Best option: Gasoline"
            }

            textResult.text = "Best option: Alcohol"
        }
    }

    private fun validateFields(pAlcohol: String, pGasoline: String): Boolean {
        textInputAlcohol.error = null
        textInputGasoline.error = null

        if (pAlcohol.isEmpty()) {
            textInputAlcohol.error = "Type alcohol price"
            return false

        } else if (pGasoline.isEmpty()) {
            textInputGasoline.error = "Type gasoline price"
            return false
        }

        return true

    }

    private fun iniateComponentsInterface() {
        textInputAlcohol = findViewById(R.id.text_input_alcohol)
        editAlcohol = findViewById(R.id.edit_alcohol)

        textInputGasoline = findViewById(R.id.text_input_gasoline)
        editGasoline = findViewById(R.id.edit_gasoline)

        btnCalculate = findViewById(R.id.button_calculate)
        textResult = findViewById(R.id.text_result)
    }
}