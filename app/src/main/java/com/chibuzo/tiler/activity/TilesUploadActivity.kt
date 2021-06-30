package com.chibuzo.tiler.activity

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityTilesUploadBinding
import com.chibuzo.tiler.utility.ActivityUtility
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class TilesUploadActivity : AppCompatActivity() {
    private val REQUEST_CODE: Int = 200
    private lateinit var imageUri: Uri
    private lateinit var updatePostImage: String
    private lateinit var binding: ActivityTilesUploadBinding
    private var photoFile: File? = null
    private val CAPTURE_IMAGE_REQUEST = 1
    private var mCurrentPhotoPath: String? = null

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
            captureCameraImage()
        }

        binding.uploadPictureButton.genericButtonLayout.setOnClickListener {

        }

        binding.uploadCustomTile.genericButtonLayout.setOnClickListener {

        }
    }

    private fun captureCameraImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (takePictureIntent.resolveActivity(packageManager) != null) {
                // Create the File where the photo should go
                try {
                    photoFile = createImageFile()
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        val photoURI = FileProvider.getUriForFile(this, "com.chibuzo.tiler.fileprovider", photoFile!!)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST)
                    }
                } catch (ex: Exception) {
                    // Error occurred while creating the File
                    displayMessage(baseContext, ex.message.toString())
                }
            } else {
                displayMessage(baseContext, "Null")
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.absolutePath
        return image
    }

    private fun displayMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val myBitmap = BitmapFactory.decodeFile(photoFile!!.absolutePath)

            Glide.with(this)
                .load(myBitmap)
                .transform(FitCenter(), RoundedCorners(11))
                .into(binding.tilesUploadImage)
        } else {
            displayMessage(baseContext, "Request cancelled or something went wrong.")
        }
    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            lateinit var bitmap: Bitmap

            Log.e("imageUriValue", "imageUriValue here is $imageUri")

            try {
                bitmap = ActivityUtility.decodeSampledBitmapFromUri(this,
                    imageUri, 333, 333)

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
                        imageUri, 333, 333)

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
*/

    private fun startImageCapture() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        resultLauncher.launch(intent)
//        startActivityForResult(intent, REQUEST_CODE)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                lateinit var bitmap: Bitmap
                val data: Intent? = result.data
                val imageData: Bitmap = data?.extras?.get("data") as Bitmap

                Glide.with(this)
                    .load(imageData)
                    .transform(FitCenter(), RoundedCorners(11))
                    .into(binding.tilesUploadImage)

/*
                try {
                    bitmap = ActivityUtility.decodeSampledBitmapFromUri(this,
                        imageUri, 333, 333)

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
                            imageUri, 333, 333)

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
*/

            }
        }
}


