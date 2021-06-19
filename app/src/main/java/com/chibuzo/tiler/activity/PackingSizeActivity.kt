package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityPackingSizeBinding

class PackingSizeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPackingSizeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPackingSizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}


