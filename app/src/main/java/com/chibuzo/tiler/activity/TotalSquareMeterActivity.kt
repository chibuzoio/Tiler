package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityTotalSquareMeterBinding

class TotalSquareMeterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTotalSquareMeterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTotalSquareMeterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.totalSquareMeterCalculator.genericHeaderText.text = "Total Square Meter Calculator";
        binding.numberOfCartonsInput.genericInputLabel.text = "Number Of Cartons";
        binding.numberOfPiecesInput.genericInputLabel.text = "Number Of Pieces";
        binding.cartonSquareMeterInput.genericInputLabel.text = "Square Meter Per Carton";
        binding.calculateCartonSize.genericButtonLabel.text = "Calculate";
    }
}


