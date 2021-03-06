package com.example.bitcoinpricetracker.model

import com.squareup.moshi.Json

/**
 * Data class used as a model for the bitcoin ticker api
 */
data class BitcoinTicker(
    @Json(name = "USD")
    val uSD: CurrencyBitcoin,

    @Json(name = "AUD")
    val aUD: CurrencyBitcoin,

    @Json(name = "BRL")
    val bRL: CurrencyBitcoin,

    @Json(name = "CAD")
    val cAD: CurrencyBitcoin,

    @Json(name = "CHF")
    val cHF: CurrencyBitcoin,

    @Json(name = "CLP")
    val cLP: CurrencyBitcoin,

    @Json(name = "CNY")
    val cNY: CurrencyBitcoin,

    @Json(name = "DKK")
    val dKK: CurrencyBitcoin,

    @Json(name = "EUR")
    val eUR: CurrencyBitcoin,

    @Json(name = "GBP")
    val gBP: CurrencyBitcoin,

    @Json(name = "HKD")
    val hKD: CurrencyBitcoin,

    @Json(name = "INR")
    val iNR: CurrencyBitcoin,

    @Json(name = "ISK")
    val iSK: CurrencyBitcoin,

    @Json(name = "JPY")
    val jPY: CurrencyBitcoin,

    @Json(name = "KRW")
    val kRW: CurrencyBitcoin,

    @Json(name = "NZD")
    val nZD: CurrencyBitcoin,

    @Json(name = "PLN")
    val pLN: CurrencyBitcoin,

    @Json(name = "RUB")
    val rUB: CurrencyBitcoin,

    @Json(name = "SEK")
    val sEK: CurrencyBitcoin,

    @Json(name = "SGD")
    val sGD: CurrencyBitcoin,

    @Json(name = "THB")
    val tHB: CurrencyBitcoin,

    @Json(name = "TRY")
    val tRY: CurrencyBitcoin,

    @Json(name = "TWD")
    val tWD: CurrencyBitcoin

) {
    /**
     * Return map for all supported currencies from the recieved ticker response
     */
    fun btcPrices(): Map<String, CurrencyBitcoin> {
        return mapOf(
            "USD" to uSD,
            "AUD" to aUD,
            "BRL" to bRL,
            "CAD" to cAD,
            "CHF" to cHF,
            "CLP" to cLP,
            "CNY" to cNY,
            "DKK" to dKK,
            "GBP" to gBP,
            "HKD" to hKD,
            "INR" to iNR,
            "ISK" to iSK,
            "JPY" to jPY,
            "KRW" to kRW,
            "NZD" to nZD,
            "PLN" to pLN,
            "RUB" to rUB,
            "SEK" to sEK,
            "SGD" to sGD,
            "THB" to tHB,
            "TRY" to tRY,
            "TWD" to tWD
        )
    }
}