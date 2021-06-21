package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityPriceByCartonBinding

class PriceByCartonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPriceByCartonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPriceByCartonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.priceByCartonCalculator.genericHeaderText.text = "Price By Carton Calculator";
        binding.numberOfCartonsInput.genericInputLabel.text = "Number Of Cartons";
        binding.numberOfPiecesInput.genericInputLabel.text = "Number Of Pieces";
        binding.pricePerCartonInput.genericInputLabel.text = "Price Per Carton";
        binding.calculatePriceByCarton.genericButtonLabel.text = "Calculate";

        
    }
}


