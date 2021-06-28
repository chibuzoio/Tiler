package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityTilesUploadBinding

class TilesUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTilesUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTilesUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}


