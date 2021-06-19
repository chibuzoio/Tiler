package com.chibuzo.tiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibuzo.tiler.databinding.ActivityGenericCatalogBinding

class GenericCatalogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenericCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGenericCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}


