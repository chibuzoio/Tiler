package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityMainBinding

class TileCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tile_calculator)
    }
}


