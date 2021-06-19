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

    }
}


