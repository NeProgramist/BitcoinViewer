package com.example.core.datasources

import com.example.core.domain.models.BitcoinUsdRate
import com.github.michaelbull.result.Result

interface BitcoinRateDataSource {
    suspend fun getBitcoinRate(): Result<BitcoinUsdRate, Exception>
    suspend fun saveBitcoinRate(bitcoinUsdRate: BitcoinUsdRate): Result<Unit, Exception>
}
