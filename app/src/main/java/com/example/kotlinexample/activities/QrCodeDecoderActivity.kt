package com.example.kotlinexample.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScanning

import android.util.Log
import android.widget.TextView
import com.example.kotlinexample.R

import com.google.mlkit.vision.common.InputImage

import com.google.mlkit.vision.barcode.common.Barcode

import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import java.lang.Exception


//Author: Mehmet İlbay
//Reference: https://developer.android.com/guide/topics/permissions/overvie
//Reference: https://developer.android.com/training/permissions/requesting#manage-request-code-yourself
//Reference: https://developers.google.com/ml-kit/vision/barcode-scanning/android#kotlin

class QrCodeDecoderActivity : AppCompatActivity() {
    private val OPEN_GALLERY_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_decode)
    }

    //Reference: https://developer.android.com/training/permissions/requesting#manage-request-code-yourself
    fun pickAQrCodeFromGallery(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                openGallery()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                Toast.makeText(
                    applicationContext,
                    R.string.qr_decoder_permission_text,
                    Toast.LENGTH_LONG
                ).show().also {
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), OPEN_GALLERY_REQUEST_CODE)
                }
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), OPEN_GALLERY_REQUEST_CODE)
            }
        }
    }

    //Reference: https://developer.android.com/training/permissions/requesting#manage-request-code-yourself
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            OPEN_GALLERY_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    openGallery()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    Toast.makeText(
                        applicationContext,
                        R.string.qr_decoder_permission_text,
                        Toast.LENGTH_LONG
                    ).show()
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    //Reference: https://developer.android.com/guide/components/intents-common#Storage
    @SuppressLint("QueryPermissionsNeeded")
    private fun openGallery() {
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickPhotoIntent.resolveActivity(packageManager)?.let {
            startActivityForResult(pickPhotoIntent, OPEN_GALLERY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.apply {
            when {
                requestCode == OPEN_GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK-> {
                     this.data?.apply {
                         decodeQRCode(this)
                     }
                 }
            }
        }
    }

    //Reference: https://developers.google.com/ml-kit/vision/barcode-scanning/android
    private fun decodeQRCode(selectedImageUri: Uri) {
        //configure scanning options to improve speed.
        val options = BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE).build()

        //load image from uri.
        val image: InputImage = try {
            InputImage.fromFilePath(applicationContext, selectedImageUri)
        } catch (e: Exception) {
            Log.i("IMAGE_CANT_BE_READ", e.message.toString())
            return
        }

        //create barcode scanner instance with options that includes expected barcode types.
        val scanner = BarcodeScanning.getClient(options)

        //decode qr code
        scanner.process(image).addOnSuccessListener { barcodes: List<Barcode> ->
            when {
                barcodes.isEmpty() -> {
                    Log.i("QR_NOT_FOUND", "Can not Find Qr Code")
                    findViewById<TextView>(R.id.qr_code_result).visibility = View.GONE
                }
                1 == barcodes.size -> {
                    Log.i("QR_FOUND", "Decode Qr Code successfully")
                    writeQrCodeResult(barcodes[0].rawValue)
                }
                else -> {
                    Log.i("FIND_MORE_THAN_ONE_QR", "Find More Than One Qr Code")
                    findViewById<TextView>(R.id.qr_code_result).visibility = View.GONE
                }
            }
        }.addOnFailureListener { e: Exception? ->
            Log.i("QR_NOT_FOUND", "Can not Find Qr Code")
            findViewById<TextView>(R.id.qr_code_result).visibility = View.GONE
        }
    }

    private fun writeQrCodeResult(qrCodeValue: String?) {
        qrCodeValue?.apply {
            findViewById<TextView>(R.id.qr_code_result).also { textView ->
                textView.text = getString(R.string.qr_code_result_text, this)
                textView.visibility = View.VISIBLE
            }
        }
    }
}