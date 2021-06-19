package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityMyCatalogBinding

class MyCatalogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}


