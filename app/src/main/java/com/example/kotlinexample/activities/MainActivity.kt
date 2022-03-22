package com.example.kotlinexample.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexample.R


//Author: Mehmet İlbay
//Reference: https://developer.android.com/training/basics/firstapp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openQrCodeDecoderActivity(view: View) {
        Intent(this, QrCodeDecoderActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun openFxListActivity(view: View) {
        Intent(this, FxListActivity::class.java).apply {
            startActivity(this)
        }
    }
}