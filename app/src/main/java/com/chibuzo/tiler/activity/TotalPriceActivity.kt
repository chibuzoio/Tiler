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

    }
}


