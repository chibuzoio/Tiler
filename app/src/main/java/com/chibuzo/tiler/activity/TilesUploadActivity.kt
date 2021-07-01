package com.chibuzo.tiler.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
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
    private val PERMISSION_CODE = 1001
    private var theBitmap: Bitmap? = null
    private var tileImageString: String? = null
    private lateinit var binding: ActivityTilesUploadBinding
    private var photoFile: File? = null
    private val PICK_IMAGE_REQUEST = 200
    private val CAPTURE_IMAGE_REQUEST = 100
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
        binding.tileAvailabilityInput.genericSwitchInputLabel.text = "Available"
        binding.phoneNumberInput.genericNumberInputLabel.text = "Phone Number"
        binding.warehouseNameInput.genericTextInputLabel.text = "Warehouse"
        binding.originCountryInput.genericTextInputLabel.text = "Made In"
        binding.uploadCustomTile.genericButtonLabel.text = "Save Tile"

        /*
        * tileNameInput
        * xDirectionInputEditor
        *yDirectionInputEditor
        *squareMeterInput
        * packingSizeInput
        *marketPriceInput
        * sellingPriceInput
        * warehouseNameInput
        * phoneNumberInput
        * originCountryInput
        * tileAvailabilityInput
        * */

        val tileName = binding.tileNameInput.genericTextInputEditor.text
        val xDirectionDimen = binding.xDirectionInputEditor.text
        val yDirectionDimen = binding.yDirectionInputEditor.text
        val squareMeter = binding.squareMeterInput.genericNumberDecimalInputEditor.text
        val packingSize = binding.packingSizeInput.genericNumberDecimalInputEditor.text
        val marketPrice = binding.marketPriceInput.genericNumberDecimalInputEditor.text
        val sellingPrice = binding.sellingPriceInput.genericNumberDecimalInputEditor.text
        val warehouseName = binding.warehouseNameInput.genericTextInputEditor.text
        val phoneNumber = binding.phoneNumberInput.genericNumberInputEditor.text
        val originCountry = binding.originCountryInput.genericTextInputEditor.text
        val tileAvailability = binding.tileAvailabilityInput.genericSwitchInputEditor.isChecked

        binding.tileAvailabilityInput.genericSwitchInputEditor.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

            } else {

            }
        }

        binding.takePictureButton.genericButtonLayout.setOnClickListener {
            captureCameraImage()
        }

        binding.uploadPictureButton.genericButtonLayout.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }

        binding.uploadCustomTile.genericButtonLayout.setOnClickListener {
            tileImageString = ActivityUtility.encodeUploadImage(theBitmap!!)

            if (tileImageString!!.isNotBlank() && tileName.isNotBlank()
                && xDirectionDimen.isNotBlank() && yDirectionDimen.isNotBlank()
                && squareMeter.isNotBlank() && packingSize.isNotBlank() && marketPrice.isNotBlank()
                && sellingPrice.isNotBlank() && warehouseName.isNotBlank()
                && phoneNumber.isNotBlank() && originCountry.isNotBlank()) {
                // post to the database
                
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun captureCameraImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (takePictureIntent.resolveActivity(packageManager) != null) {
                try {
                    photoFile = createImageFile()

                    if (photoFile != null) {
                        val photoURI = FileProvider.getUriForFile(this, "com.chibuzo.tiler.fileprovider", photoFile!!)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST)
                    }
                } catch (ex: Exception) {
                    displayMessage(baseContext, ex.message.toString())
                }
            } else {
                displayMessage(baseContext, "Null")
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )

        mCurrentPhotoPath = image.absolutePath
        return image
    }

    private fun displayMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            theBitmap = BitmapFactory.decodeFile(photoFile!!.absolutePath)

            Glide.with(this)
                .load(theBitmap)
                .transform(FitCenter(), RoundedCorners(11))
                .into(binding.tilesUploadImage)
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            Glide.with(this)
                .load(data?.data)
                .transform(FitCenter(), RoundedCorners(11))
                .into(binding.tilesUploadImage)

            theBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                val source = ImageDecoder.createSource(contentResolver, data?.data!!)
                ImageDecoder.decodeBitmap(source)
            } else{
                MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
            }
        } else {
            displayMessage(baseContext, "Request cancelled or something went wrong.")
        }
    }
}


