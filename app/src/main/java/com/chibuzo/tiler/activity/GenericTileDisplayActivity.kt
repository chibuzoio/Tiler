package com.chibuzo.tiler.activity

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityGenericTileDisplayBinding
import java.io.File

class GenericTileDisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenericTileDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGenericTileDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tileDisplayDimension.genericHeader.text = "Dimension"
        binding.tileDisplaySquareMeter.genericHeader.text = "Square Meter Per Carton"
        binding.tileDisplayPackingSize.genericHeader.text = "Packing Size Per Carton"
        binding.tileDisplaySellingPrice.genericHeader.text = "Selling Price"
        binding.tileDisplayWarehouse.genericHeader.text = "W"
        binding.tileDisplayOriginCountry.genericHeader.text = "Made In"
        binding.tileDisplayMarketPrice.genericHeader.text = "MP"
        binding.tileDisplayPhoneNumber.genericHeader.text = "Phone Number"

        val bundle = intent.extras
        val contextWrapper = ContextWrapper(this)
        val directory = contextWrapper.getDir("imageDir", Context.MODE_PRIVATE)
        val file = File(directory, bundle?.get("tileImageName").toString())

        Glide.with(this)
            .load(file)
            .transform(FitCenter(), RoundedCorners(11))
            .into(binding.tileDisplayImage)

        binding.tileDisplayName.text = bundle?.get("tileName").toString()
        binding.tileDisplayDimension.genericBody.text = bundle?.get("dimension").toString()
        binding.tileDisplaySquareMeter.genericBody.text = bundle?.get("tileSquareMeter").toString()
        binding.tileDisplayPackingSize.genericBody.text = bundle?.get("packingSize").toString()
        val sellingPrice = "#${bundle?.get("sellingPrice").toString()}"
        binding.tileDisplaySellingPrice.genericBody.text = sellingPrice
        binding.tileDisplayWarehouse.genericBody.text = bundle?.get("warehouseName").toString()
        binding.tileDisplayOriginCountry.genericBody.text = bundle?.get("originCountry").toString()
        val marketPrice = "#${bundle?.get("marketPrice").toString()}"
        binding.tileDisplayMarketPrice.genericBody.text = marketPrice
        binding.tileDisplayPhoneNumber.genericBody.text = bundle?.get("phoneNumber").toString()
    }
}


