package com.example.data.framework.local

import com.example.core.datasources.BitcoinRateDataSource
import com.example.core.domain.models.BitcoinUsdRate
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import java.lang.IllegalStateException

class BitcoinRateLocalDataSource(
    private val prefManager: PreferenceManager,
) : BitcoinRateDataSource {
    private var cachedRate: Float = RATE_DEFAULT

    override suspend fun getBitcoinRate(): Result<BitcoinUsdRate, Exception> {
        return if (cachedRate != RATE_DEFAULT) {
            Ok(BitcoinUsdRate(cachedRate))
        } else {
            val rate = prefManager.getFloat(RATE_PREF, RATE_DEFAULT)

            if (rate != RATE_DEFAULT) {
                cachedRate = rate
                Ok(BitcoinUsdRate(rate))
            } else {
                Err(IllegalStateException("No cached value"))
            }
        }
    }

    override suspend fun saveBitcoinRate(
        bitcoinUsdRate: BitcoinUsdRate
    ): Result<Unit, Exception> {
        cachedRate = bitcoinUsdRate.rate
        prefManager.putFloat(RATE_PREF, bitcoinUsdRate.rate)

        return Ok(Unit)
    }

    companion object {
        private const val RATE_PREF = "RATE_PREF"
        private const val RATE_DEFAULT = -1f
    }
}
