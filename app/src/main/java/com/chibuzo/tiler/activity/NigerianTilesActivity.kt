package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityNigerianTilesBinding

class NigerianTilesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNigerianTilesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigerian_tiles)

        binding = ActivityNigerianTilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}


