package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityCartonSizeBinding

class CartonSizeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartonSizeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartonSizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cartonSizeCalculator.genericHeaderText.text = "Total Cartons And Pieces Calculator";
        binding.totalSquareMeterInput.genericInputLabel.text = "Total Square Meter";
        binding.cartonSquareMeterInput.genericInputLabel.text = "Square Meter Per Carton";
        binding.calculateCartonSize.genericButtonLabel.text = "Calculate";
    }
}


