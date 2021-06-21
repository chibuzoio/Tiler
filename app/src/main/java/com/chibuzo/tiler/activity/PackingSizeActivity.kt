package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityPackingSizeBinding
import kotlin.math.roundToInt

class PackingSizeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPackingSizeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPackingSizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.packingSizeCalculator.genericHeaderText.text = "Pieces Per Carton Calculator";
        binding.tileLengthInput.genericInputLabel.text = "Length (in centimeter)";
        binding.tileWidthInput.genericInputLabel.text = "Width (in centimeter)";
        binding.cartonSquareMeterInput.genericInputLabel.text = "Square Meter Per Carton";
        binding.calculatePackingSize.genericButtonLabel.text = "Calculate";

        val tileWidth = binding.tileWidthInput.genericInputEditor.text
        val tileLength = binding.tileLengthInput.genericInputEditor.text
        val cartonSquareMeter = binding.cartonSquareMeterInput.genericInputEditor.text

        binding.calculatePackingSize.genericButtonLayout.setOnClickListener {
            if (cartonSquareMeter.isNotBlank() &&
                    tileWidth.isNotBlank() && tileLength.isNotBlank()) {
                val tileWidthValue = tileWidth.toString().toDouble().div(100)
                val tileLengthValue = tileLength.toString().toDouble().div(100)
                val cartonSquareMeterValue = cartonSquareMeter.toString().toDouble()

                val packingSizeResult =
                    cartonSquareMeterValue.div(tileLengthValue).div(tileWidthValue).roundToInt()

                binding.calculationResultLayout.calculationResultText.text =
                    "$packingSizeResult Tiles Per Carton"
            }
        }
    }
}


