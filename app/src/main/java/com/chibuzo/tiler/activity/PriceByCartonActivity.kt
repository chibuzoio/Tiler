package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityPriceByCartonBinding
import java.math.BigDecimal
import java.math.RoundingMode

class PriceByCartonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPriceByCartonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPriceByCartonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.priceByCartonCalculator.genericHeaderText.text = "Price By Carton Calculator";
        binding.numberOfCartonsInput.genericInputLabel.text = "Number Of Cartons";
        binding.numberOfPiecesInput.genericInputLabel.text = "Number Of Pieces";
        binding.numberOfPiecesPerCartonInput.genericInputLabel.text = "Number Of Pieces Per Carton";
        binding.pricePerCartonInput.genericInputLabel.text = "Price Per Carton";
        binding.calculatePriceByCarton.genericButtonLabel.text = "Calculate";

        val numberOfCartons = binding.numberOfCartonsInput.genericInputEditor.text
        val numberOfPieces = binding.numberOfPiecesInput.genericInputEditor.text
        val numberOfPiecesPerCarton = binding.numberOfPiecesPerCartonInput.genericInputEditor.text
        val pricePerCarton = binding.pricePerCartonInput.genericInputEditor.text

        binding.calculatePriceByCarton.genericButtonLayout.setOnClickListener {
            if (numberOfCartons.isNotBlank() && numberOfPieces.isNotBlank() &&
                numberOfPiecesPerCarton.isNotBlank() && pricePerCarton.isNotBlank()) {
                val numberOfPiecesValue = numberOfPieces.toString().toInt()
                val numberOfCartonsValue = numberOfCartons.toString().toInt()
                val pricePerCartonValue = pricePerCarton.toString().toDouble()
                val numberOfPiecesPerCartonValue = numberOfPiecesPerCarton.toString().toInt()

                var pricePerCartonResult = pricePerCartonValue.times(numberOfCartonsValue)
                val totalPiecesPrice =
                    (pricePerCartonValue.div(numberOfPiecesPerCartonValue)).times(numberOfPiecesValue)

                pricePerCartonResult = pricePerCartonResult.plus(totalPiecesPrice)
                val roundedPricePerCarton =
                    BigDecimal(pricePerCartonResult).setScale(2, RoundingMode.HALF_EVEN)

                binding.calculationResultLayout.calculationResultText.text =
                    "$roundedPricePerCarton Naira"
            }
        }
    }
}


