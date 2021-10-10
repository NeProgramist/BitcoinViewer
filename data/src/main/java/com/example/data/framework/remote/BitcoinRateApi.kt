package com.example.data.framework.remote

import retrofit2.http.GET

interface BitcoinRateApi {

    @GET("bpi/currentprice.json")
    suspend fun getBitcoinCurrency(): BitcoinCurrencyResponse
}
