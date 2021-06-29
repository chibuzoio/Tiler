package com.chibuzo.tiler.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityTilesUploadBinding
import com.chibuzo.tiler.utility.ActivityUtility


class TilesUploadActivity : AppCompatActivity() {
    private lateinit var updatePostImage: String
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

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                lateinit var bitmap: Bitmap
                val data: Intent? = result.data

                Log.e("dataValue", "Value of data here is ${data?.data}")

                try {
                    bitmap = ActivityUtility.decodeSampledBitmapFromUri(this,
                        data?.data!!, 333, 333)

                    Glide.with(this)
                        .load(bitmap)
                        .transform(FitCenter(), RoundedCorners(11))
                        .into(binding.tilesUploadImage)

                    updatePostImage = ActivityUtility.encodeUploadImage(bitmap)
                } catch (exception: Exception) {
                    exception.printStackTrace()

                    Log.e("firstException", "Exception here is $exception")

                    try {
                        bitmap = ActivityUtility.getHigherVersionBitmapImage(this,
                            data?.data!!, 333, 333)

                        Glide.with(this)
                            .load(bitmap)
                            .transform(FitCenter(), RoundedCorners(11))
                            .into(binding.tilesUploadImage)

                        updatePostImage = ActivityUtility.encodeUploadImage(bitmap)
                    } catch (localException: Exception) {
                        localException.printStackTrace()

                        Log.e("secondException", "Exception here is $exception")
                    }
                }
            }
        }
}


