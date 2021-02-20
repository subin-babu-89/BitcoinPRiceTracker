package com.example.bitcoinpricetracker.model

import com.squareup.moshi.Json

data class CurrencyBitcoin(
    @Json(name = "15m")
    val _15m: Double,

    @Json(name = "last")
    val last: Double,

    @Json(name = "buy")
    val buy: Double,

    @Json(name = "sell")
    val sell: Double,

    @Json(name = "symbol")
    val symbol: String
)