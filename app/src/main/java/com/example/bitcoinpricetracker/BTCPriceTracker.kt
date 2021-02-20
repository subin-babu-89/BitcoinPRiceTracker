package com.example.bitcoinpricetracker

import android.app.Application
import timber.log.Timber

class BTCPriceTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}