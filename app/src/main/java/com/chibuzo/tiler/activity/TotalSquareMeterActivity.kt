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
        binding.numberOfCartonsInput.genericNumberInputLabel.text = "Number Of Cartons";
        binding.numberOfPiecesInput.genericNumberInputLabel.text = "Number Of Pieces";
        binding.cartonSquareMeterInput.genericNumberInputLabel.text = "Square Meter Per Carton";
        binding.numberOfPiecesPerCarton.genericNumberInputLabel.text = "Number Of Pieces Per Carton"
        binding.calculateTotalSquareMeter.genericButtonLabel.text = "Calculate";

        val numberOfPieces = binding.numberOfPiecesInput.genericNumberInputEditor.text
        val numberOfCartons = binding.numberOfCartonsInput.genericNumberInputEditor.text
        val piecesPerCarton = binding.numberOfPiecesPerCarton.genericNumberInputEditor.text
        val cartonSquareMeter = binding.cartonSquareMeterInput.genericNumberInputEditor.text

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


