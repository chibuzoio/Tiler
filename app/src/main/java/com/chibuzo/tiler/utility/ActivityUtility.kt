package com.chibuzo.tiler.utility

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream


class ActivityUtility {

    companion object {
        private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
            var inSampleSize = 1;
            val width = options.outWidth
            val height = options.outHeight

            if (height > reqHeight || width > reqWidth) {
                val halfWidth = width.div(2)
                val halfHeight = height.div(2)

                while ((halfHeight.div(inSampleSize)) >= reqHeight && (halfWidth.div(inSampleSize)) >= reqWidth) {
                    inSampleSize = inSampleSize.times(2)
                }
            }

            return inSampleSize
        }

        private fun getContentURIRealPath(context: Context, imageUri: Uri): String {
            lateinit var result: String
            val cursor: Cursor? = context.contentResolver.query(imageUri, null, null, null, null)

            if (cursor == null) {
                result = imageUri.path.toString()
            } else {
                cursor.moveToFirst()
                val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                result = cursor.getString(index)
                cursor.close()
            }

            return result
        }

        private fun getContentRealPathFromUri(context: Context, imageUri: Uri): String {
            lateinit var gottenImagePath: String
            val scheme: String? = imageUri.scheme

            if (ContentResolver.SCHEME_CONTENT == scheme) {
                var cursor = context.contentResolver.query(imageUri, null, null, null, null)

                cursor?.moveToFirst()

                var imageId = cursor!!.getString(0)

                imageId = imageId.substring(imageId.lastIndexOf(":") + 1)

                cursor.close()

                cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media._ID + " = ?", arrayOf(imageId), null)

                cursor?.moveToFirst()

                gottenImagePath = cursor?.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)).toString()

                cursor?.close()
            } else if (ContentResolver.SCHEME_FILE == scheme) {
                gottenImagePath = imageUri.path.toString()
            }

            return gottenImagePath
        }

        fun getHigherVersionBitmapImage(context: Context, imageUri: Uri, reqWidth: Int, reqHeight: Int): Bitmap {
            lateinit var bitmap: Bitmap

            try {
                val options = BitmapFactory.Options()
                val file = File(getContentRealPathFromUri(context, imageUri))
                var iStream: InputStream = FileInputStream(file)

                options.inJustDecodeBounds = true
                BitmapFactory.decodeStream(iStream, null, options)

                iStream.close()

                options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

                iStream = FileInputStream(file)

                options.inJustDecodeBounds = false
                bitmap = BitmapFactory.decodeStream(iStream, null, options)!!
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

            return bitmap
        }

        fun decodeSampledBitmapFromUri(context: Context, imageUri: Uri, reqWidth: Int, reqHeight: Int): Bitmap {
            lateinit var bitmap: Bitmap

            try {
                val options = BitmapFactory.Options()
                val file = File(getContentURIRealPath(context, imageUri))
                var iStream: InputStream = FileInputStream(file)

                options.inJustDecodeBounds = true
                BitmapFactory.decodeStream(iStream, null, options)

                iStream.close()

                options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

                iStream = FileInputStream(file)

                options.inJustDecodeBounds = false
                bitmap = BitmapFactory.decodeStream(iStream, null, options)!!
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

            return bitmap
        }
    }
}


