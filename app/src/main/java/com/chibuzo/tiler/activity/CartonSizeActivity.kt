package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityCartonSizeBinding
import kotlin.math.floor
import kotlin.math.roundToInt

class CartonSizeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartonSizeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartonSizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cartonSizeCalculator.genericHeaderText.text = "Total Cartons And Pieces Calculator";
        binding.totalSquareMeterInput.genericNumberDecimalInputLabel.text = "Total Square Meter";
        binding.cartonSquareMeterInput.genericNumberDecimalInputLabel.text = "Square Meter Per Carton";
        binding.numberOfPiecesPerCartonInput.genericNumberDecimalInputLabel.text = "Number Of Pieces Per Carton";
        binding.calculateCartonSize.genericButtonLabel.text = "Calculate";

        val totalSquareMeter = binding.totalSquareMeterInput.genericNumberDecimalInputEditor.text
        val cartonSquareMeter = binding.cartonSquareMeterInput.genericNumberDecimalInputEditor.text
        val numberOfPiecesPerCarton = binding.numberOfPiecesPerCartonInput.genericNumberDecimalInputEditor.text

        binding.calculateCartonSize.genericButtonLayout.setOnClickListener{
            if (totalSquareMeter.isNotBlank() &&
                cartonSquareMeter.isNotBlank() && numberOfPiecesPerCarton.isNotBlank()) {
                val numberOfPiecesValue = numberOfPiecesPerCarton.toString().toInt()
                val totalSquareMeterValue = totalSquareMeter.toString().toDouble()
                val cartonSquareMeterValue = cartonSquareMeter.toString().toDouble()

                val cartonSizeFullResult = totalSquareMeterValue.div(cartonSquareMeterValue)
                val cartonSizeResult = (floor(cartonSizeFullResult)).roundToInt()
                val remainingPieces = ((cartonSizeFullResult % 1) * numberOfPiecesValue).roundToInt()

                if (remainingPieces > 1) {
                    binding.calculationResultLayout.calculationResultText.text =
                        "$cartonSizeResult cartons and $remainingPieces pieces!";
                } else {
                    binding.calculationResultLayout.calculationResultText.text =
                        "$cartonSizeResult cartons and $remainingPieces piece!";
                }
            }
        }
    }
}


