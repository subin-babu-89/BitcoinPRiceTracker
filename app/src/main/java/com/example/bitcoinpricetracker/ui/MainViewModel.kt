package com.example.bitcoinpricetracker.ui

import android.os.SystemClock
import androidx.lifecycle.*
import com.example.bitcoinpricetracker.model.BitcoinTicker
import com.example.bitcoinpricetracker.network.BitcoinTrackerService
import kotlinx.coroutines.launch
import java.util.*
private const val ONE_SECOND: Long = 1000

/**
 * Shared viewmodel to be used for the mainfragment and the viewpager fragments
 */
class MainViewModel(private val btcService: BitcoinTrackerService) : ViewModel() {
    private var _bitcoinTicker = MutableLiveData<BitcoinTicker>()
    val bitcoinTicker: LiveData<BitcoinTicker>
        get() = _bitcoinTicker

    private var _initialTime: Long = SystemClock.elapsedRealtime()
    val initialTime: Long
        get() = _initialTime

    private val timer: Timer = Timer()

    init {
        //initialize java timer class with the start of the viewmodel
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    getLatestBTCPrice()
                }
            }, ONE_SECOND, ONE_SECOND
        )
    }

    /**
     * Function used to fetch the bitcoin prices remotely
     */
    fun getLatestBTCPrice() {
        viewModelScope.launch {
            val bitcoinTicker = btcService.getBitcoinTicker()
            _bitcoinTicker.value = bitcoinTicker
        }
    }

    /**
     * Cancel timer when the viewmodel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    /**
     * Factory class used to initialize the MainViewModel
     */
    class Factory(private val btcService: BitcoinTrackerService) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(btcService) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}