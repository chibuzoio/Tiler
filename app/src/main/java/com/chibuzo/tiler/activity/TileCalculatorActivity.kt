package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityMainBinding
import com.chibuzo.tiler.databinding.ActivityTileCalculatorBinding

class TileCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTileCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTileCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cartonSizeMenu.genericMenuText.text = "Tile Carton Size";
        binding.totalSquareMeterMenu.genericMenuText.text = "Tile Total Square Meter";
        binding.totalPriceMenu.genericMenuText.text = "Tile Total Price";
        binding.squareMeterMenu.genericMenuText.text = "Tile Square Meter";
        binding.packingSizeMenu.genericMenuText.text = "Tile Packing Size";
    }
}


