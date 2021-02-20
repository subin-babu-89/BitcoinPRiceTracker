package com.example.bitcoinpricetracker.ui

import android.os.SystemClock
import androidx.lifecycle.*
import com.example.bitcoinpricetracker.model.BitcoinTicker
import com.example.bitcoinpricetracker.network.BitcoinTrackerService
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.*

class MainViewModel(private val btcService: BitcoinTrackerService) : ViewModel() {

    private val ONE_SECOND: Long = 1000

    private var _bitcoinTicker = MutableLiveData<BitcoinTicker>()
    val bitcoinTicker: LiveData<BitcoinTicker>
        get() = _bitcoinTicker

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime



    private val _currencyValues = MutableLiveData<List<String>>()
    val currencyValues : LiveData<List<String>>
        get() = _currencyValues

    private var initialTime: Long = SystemClock.elapsedRealtime()
    val startTime : Long
        get() = initialTime
    private val timer: Timer = Timer()

    init {


        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    val newValue: Long = (SystemClock.elapsedRealtime() - initialTime) / ONE_SECOND
                    _elapsedTime.postValue(newValue)
                    getLatestBTCPrice()
                }
            }, ONE_SECOND, ONE_SECOND
        )
    }

    fun getLatestBTCPrice() {
        viewModelScope.launch {
            val bitcoinTicker = btcService.getBitcoinTicker()
            _bitcoinTicker.value = bitcoinTicker
            _currencyValues.value = bitcoinTicker.btcLatestPrices()
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
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