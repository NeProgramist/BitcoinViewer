package com.example.data.framework.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BitcoinCurrencyResponse(
    val bpi: Bpi,
)

@Serializable
data class Bpi(
    @SerialName("USD") val usd: USD,
)

@Serializable
data class USD(
    @SerialName("rate_float") val rateFloat: Float,
)
