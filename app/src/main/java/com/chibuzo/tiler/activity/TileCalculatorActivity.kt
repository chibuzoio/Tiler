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

        binding.cartonSizeMenu.genericMenuText.text = "Carton Size Calculator";
        binding.totalSquareMeterMenu.genericMenuText.text = "Total Square Meter Calculator";
        binding.pricePerSquareMeterMenu.genericMenuText.text = "Price Per Square Meter Calculator";
        binding.pricePerCartonMenu.genericMenuText.text = "Price Per Carton Calculator";
        binding.squareMeterMenu.genericMenuText.text = "Square Meter Calculator";
        binding.packingSizeMenu.genericMenuText.text = "Packing Size Calculator";

        binding.cartonSizeMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, CartonSizeActivity::class.java)
            startActivity(intent)
        }

        binding.totalSquareMeterMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, TotalSquareMeterActivity::class.java)
            startActivity(intent)
        }

        binding.pricePerSquareMeterMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, PriceBySquareMeterActivity::class.java)
            startActivity(intent)
        }

        binding.pricePerCartonMenu.genericMenuLayout.setOnClickListener{
            val intent = Intent(this, PriceByCartonActivity::class.java)
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


