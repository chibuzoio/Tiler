package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivitySquareMeterBinding
import java.math.BigDecimal
import java.math.RoundingMode

class SquareMeterActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySquareMeterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySquareMeterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.squareMeterCalculator.genericHeaderText.text = "Square Meter Per Carton Calculator";
        binding.tileLengthInput.genericInputLabel.text = "Length (in centimeter)";
        binding.tileWidthInput.genericInputLabel.text = "Width (in centimeter)";
        binding.numberOfPiecesPerCartonInput.genericInputLabel.text = "Number Of Pieces Per Carton";
        binding.calculateSquareMeter.genericButtonLabel.text = "Calculate";

        val tileWidth = binding.tileWidthInput.genericInputEditor.text
        val tileLength = binding.tileLengthInput.genericInputEditor.text
        val numberOfPiecesPerCarton = binding.numberOfPiecesPerCartonInput.genericInputEditor.text

        binding.calculateSquareMeter.genericButtonLayout.setOnClickListener {
            if (numberOfPiecesPerCarton.isNotBlank() &&
                tileWidth.isNotBlank() && tileLength.isNotBlank()) {
                val tileWidthValue = tileWidth.toString().toDouble().div(100)
                val tileLengthValue = tileLength.toString().toDouble().div(100)
                val numberOfPiecesPerCartonValue = numberOfPiecesPerCarton.toString().toInt()

                val tileSquareMeterResult =
                    tileWidthValue.times(tileLengthValue).times(numberOfPiecesPerCartonValue)
                val roundedSquareMeter =
                    BigDecimal(tileSquareMeterResult).setScale(2, RoundingMode.HALF_EVEN)

                binding.calculationResultLayout.calculationResultText.text =
                    "$roundedSquareMeter Square Meter"
            }
        }
    }
}


