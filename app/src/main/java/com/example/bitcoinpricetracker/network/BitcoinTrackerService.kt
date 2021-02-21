package com.example.bitcoinpricetracker.network

import com.example.bitcoinpricetracker.model.BitcoinTicker
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Network interface to fetch the latest blockchain prices from the server
 */
interface BitcoinTrackerService {
    companion object {
        private const val BASE_URL = "https://blockchain.info/"

        fun create(): BitcoinTrackerService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(
                MoshiConverterFactory.create(moshi)
            ).build().create(BitcoinTrackerService::class.java)
        }
    }

    @GET("ticker")
    suspend fun getBitcoinTicker(): BitcoinTicker
}