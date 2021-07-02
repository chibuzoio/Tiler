package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityTotalSquareMeterBinding
import kotlin.math.floor
import kotlin.math.roundToInt

class TotalSquareMeterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTotalSquareMeterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTotalSquareMeterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.totalSquareMeterCalculator.genericHeaderText.text = "Total Square Meter Calculator";
        binding.numberOfCartonsInput.genericNumberDecimalInputLabel.text = "Number Of Cartons";
        binding.numberOfPiecesInput.genericNumberDecimalInputLabel.text = "Number Of Pieces";
        binding.cartonSquareMeterInput.genericNumberDecimalInputLabel.text = "Square Meter Per Carton";
        binding.numberOfPiecesPerCarton.genericNumberDecimalInputLabel.text = "Packing Size"
        binding.calculateTotalSquareMeter.genericButtonLabel.text = "Calculate";

        val numberOfPieces = binding.numberOfPiecesInput.genericNumberDecimalInputEditor.text
        val numberOfCartons = binding.numberOfCartonsInput.genericNumberDecimalInputEditor.text
        val piecesPerCarton = binding.numberOfPiecesPerCarton.genericNumberDecimalInputEditor.text
        val cartonSquareMeter = binding.cartonSquareMeterInput.genericNumberDecimalInputEditor.text

        binding.calculateTotalSquareMeter.genericButtonLayout.setOnClickListener {
            if (numberOfPieces.isNotBlank() && numberOfCartons.isNotBlank() &&
                cartonSquareMeter.isNotBlank() && piecesPerCarton.isNotBlank()) {
                val numberOfCartonsValue = numberOfCartons.toString().toInt()
                val piecesPerCartonValue = piecesPerCarton.toString().toInt()
                val numberOfPiecesValue = numberOfPieces.toString().toDouble()
                val cartonSquareMeterValue = cartonSquareMeter.toString().toDouble()

                val totalSquareMeterWhole: Double = numberOfCartonsValue.times(cartonSquareMeterValue)
                val totalSquareMeterDecimal: Double = numberOfPiecesValue.div(piecesPerCartonValue)
                val totalSquareMeterResult = totalSquareMeterWhole.plus(totalSquareMeterDecimal)

                binding.calculationResultLayout.calculationResultText.text =
                    "$totalSquareMeterResult Square Meter"
            }
        }
    }
}


