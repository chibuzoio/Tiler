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
        binding.numberOfCartonsInput.genericNumberInputLabel.text = "Number Of Cartons";
        binding.numberOfPiecesInput.genericNumberInputLabel.text = "Number Of Pieces";
        binding.numberOfPiecesPerCartonInput.genericNumberInputLabel.text = "Number Of Pieces Per Carton";
        binding.pricePerCartonInput.genericNumberInputLabel.text = "Price Per Carton";
        binding.calculatePriceByCarton.genericButtonLabel.text = "Calculate";

        val numberOfCartons = binding.numberOfCartonsInput.genericNumberInputEditor.text
        val numberOfPieces = binding.numberOfPiecesInput.genericNumberInputEditor.text
        val numberOfPiecesPerCarton = binding.numberOfPiecesPerCartonInput.genericNumberInputEditor.text
        val pricePerCarton = binding.pricePerCartonInput.genericNumberInputEditor.text

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


