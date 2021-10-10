package com.example.data.framework.remote

import android.util.Log
import com.example.core.datasources.BitcoinRateDataSource
import com.example.core.domain.models.BitcoinUsdRate
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class BitcoinRateRemoteDataSource(
    private val api: BitcoinRateApi,
) : BitcoinRateDataSource {
    override suspend fun getBitcoinRate(): Result<BitcoinUsdRate, Exception> = try {
        val currency = api.getBitcoinCurrency().bpi.usd.rateFloat
        Ok(BitcoinUsdRate(currency))
    } catch (e: Exception) {
        Log.e(this::class.java.simpleName, e.message ?: "No message")
        Err(e)
    }

    override suspend fun saveBitcoinRate(bitcoinUsdRate: BitcoinUsdRate) = Err(Exception("Stub!"))
}
