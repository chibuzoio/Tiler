package com.chibuzo.tiler.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityPriceBySquareMeterBinding
import java.math.BigDecimal
import java.math.RoundingMode

class PriceBySquareMeterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPriceBySquareMeterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPriceBySquareMeterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.priceBySquareMeterCalculator.genericHeaderText.text = "Price By Square Meter Calculator";
        binding.totalSquareMeterInput.genericInputLabel.text = "Total Square Meter";
        binding.pricePerSquareMeterInput.genericInputLabel.text = "Price Per Square Meter";
        binding.calculatePriceBySquareMeter.genericButtonLabel.text = "Calculate";

        val totalSquareMeter = binding.totalSquareMeterInput.genericInputEditor.text
        val pricePerSquareMeter = binding.pricePerSquareMeterInput.genericInputEditor.text

        binding.totalSquareMeterInput.genericInputEditor.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(input: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(input: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(input: Editable) {

            }
        })

        binding.calculatePriceBySquareMeter.genericButtonLayout.setOnClickListener {
            if (totalSquareMeter.isNotBlank() && pricePerSquareMeter.isNotBlank()) {
                val totalSquareMeterValue = totalSquareMeter.toString().toDouble()
                val pricePerSquareMeterValue = pricePerSquareMeter.toString().toDouble()

                val pricePerSquareMeterResult = totalSquareMeterValue.times(pricePerSquareMeterValue)
                val roundedPricePerSquareMeter = BigDecimal(pricePerSquareMeterResult).setScale(2, RoundingMode.HALF_EVEN)

                binding.calculationResultLayout.calculationResultText.text =
                    "$roundedPricePerSquareMeter Naira"
            }
        }
    }
}


