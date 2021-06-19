package com.chibuzo.tiler.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.cartonSizeMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, CartonSizeActivity::class.java)
            startActivity(intent)
        }

        binding.totalSquareMeterMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, TotalSquareMeterActivity::class.java)
            startActivity(intent)
        }

        binding.totalPriceMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, TotalPriceActivity::class.java)
            startActivity(intent)
        }

        binding.squareMeterMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, SquareMeterActivity::class.java)
            startActivity(intent)
        }

        binding.packingSizeMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, PackingSizeActivity::class.java)
            startActivity(intent)
        }
    }
}


