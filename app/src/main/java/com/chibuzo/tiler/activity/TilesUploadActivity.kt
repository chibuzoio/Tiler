package com.chibuzo.tiler.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityTilesUploadBinding

class TilesUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTilesUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTilesUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.placeholder
                )
            )
            .transform(FitCenter(), RoundedCorners(11))
            .into(binding.tilesUploadImage)

        binding.uploadPictureButton.genericButtonLabel.text = "Upload Picture"
        binding.takePictureButton.genericButtonLabel.text = "Take Picture"
        binding.tileNameInput.genericTextInputLabel.text = "Tile Name"
        binding.dimensionInputLabel.text = "Dimension"
        binding.squareMeterInput.genericNumberDecimalInputLabel.text = "Square Meter Per Carton"
        binding.packingSizeInput.genericNumberDecimalInputLabel.text = "Packing Size Per Carton"
        binding.marketPriceInput.genericNumberDecimalInputLabel.text = "Market Price"
        binding.sellingPriceInput.genericNumberDecimalInputLabel.text = "Selling Price"
        binding.phoneNumberInput.genericNumberInputLabel.text = "Phone Number"
        binding.warehouseNameInput.genericTextInputLabel.text = "Warehouse"
        binding.originCountryInput.genericTextInputLabel.text = "Made In"
        binding.uploadCustomTile.genericButtonLabel.text = "Save Tile"

        binding.takePictureButton.genericButtonLayout.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(intent)
        }

        binding.uploadPictureButton.genericButtonLayout.setOnClickListener {

        }

        binding.uploadCustomTile.genericButtonLayout.setOnClickListener {

        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

            }
        }
}


