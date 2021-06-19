package com.chibuzo.tiler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.chibuzo.tiler.activity.GenericCatalogActivity
import com.chibuzo.tiler.activity.MyCatalogActivity
import com.chibuzo.tiler.activity.NigerianTilesActivity
import com.chibuzo.tiler.activity.TileCalculatorActivity
import com.chibuzo.tiler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)

                binding.tileCalculator.setOnClickListener {
                    val intent = Intent(this, TileCalculatorActivity::class.java)
                    startActivity(intent)
                }

                binding.nigerianTiles.setOnClickListener{
                    val intent = Intent(this, NigerianTilesActivity::class.java)
                    startActivity(intent)
                }

                binding.myCatalog.setOnClickListener{
                    val intent = Intent(this, MyCatalogActivity::class.java)
                    startActivity(intent)
                }

                binding.bnCatalog.setOnClickListener{
                    val intent = Intent(this, GenericCatalogActivity::class.java)
                    intent.putExtra("catalogType", "BNCatalog")
                    startActivity(intent)
                }

                binding.goldenCrownCatalog.setOnClickListener{
                    val intent = Intent(this, GenericCatalogActivity::class.java)
                    intent.putExtra("catalogType", "GoldenCrownCatalog")
                    startActivity(intent)
                }

                binding.goodwillCatalog.setOnClickListener{
                    val intent = Intent(this, GenericCatalogActivity::class.java)
                    intent.putExtra("catalogType", "GoodwillCatalog")
                    startActivity(intent)
                }

                binding.timeCatalog.setOnClickListener{
                    val intent = Intent(this, GenericCatalogActivity::class.java)
                    intent.putExtra("catalogType", "TimeCatalog")
                    startActivity(intent)
                }
            }, 2000)
    }
}


