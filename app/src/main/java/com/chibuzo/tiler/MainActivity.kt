package com.chibuzo.tiler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

                val includedHeader = binding.includedHeader

                binding.tileCalculator.setOnClickListener {

                }

                binding.nigerianTiles.setOnClickListener{

                }
            }, 2000)
    }
}


