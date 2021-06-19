package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityTotalPriceBinding

class TotalPriceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTotalPriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTotalPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.totalPriceCalculator.genericHeaderText.text = "Total Price Calculator";
        binding.cartonSquareMeterInput.genericInputLabel.text = "Square Meter Per Carton";
        binding.totalSquareMeterInput.genericInputLabel.text = "Total Square Meter";
        binding.numberOfCartonsInput.genericInputLabel.text = "Number Of Cartons";
        binding.numberOfPiecesInput.genericInputLabel.text = "Number Of Pieces";
        binding.calculateTotalPrice.genericButtonLabel.text = "Calculate";
    }
}


