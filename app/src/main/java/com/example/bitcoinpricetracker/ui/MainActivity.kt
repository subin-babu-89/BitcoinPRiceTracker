package com.example.bitcoinpricetracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bitcoinpricetracker.R

/**
 * Main (single) activity
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}