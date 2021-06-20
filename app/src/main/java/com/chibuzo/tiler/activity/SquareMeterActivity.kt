package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivitySquareMeterBinding

class SquareMeterActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySquareMeterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySquareMeterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.squareMeterCalculator.genericHeaderText.text = "Square Meter Per Carton Calculator";
        binding.tileLengthInput.genericInputLabel.text = "Length (in centimeter)";
        binding.tileWidthInput.genericInputLabel.text = "Width (in centimeter)";
        binding.numberOfPiecesInput.genericInputLabel.text = "Number Of Pieces Per Carton";
        binding.calculateSquareMeter.genericButtonLabel.text = "Calculate";
    }
}


