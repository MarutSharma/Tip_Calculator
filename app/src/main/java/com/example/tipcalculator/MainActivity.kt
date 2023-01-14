package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {
        val stringInTheTextField = binding.costOfService.text.toString()
        val cost = stringInTheTextField.toDoubleOrNull() ?: return
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        var total = cost + tip
        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
            total = ceil(total)
        } else {
            return
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        binding.totalAmount.text = getString(R.string.total_amount, formattedTotal)
        // CAN BE ADDED BUT TOTALLY DEPEND ON THE  CHOICE OF PROGRAMMER AND USER --------->
        // binding.costOfService.setText("")

    }
}