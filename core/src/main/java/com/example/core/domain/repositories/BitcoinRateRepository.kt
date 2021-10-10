package com.example.core.domain.repositories

import com.example.core.datasources.BitcoinRateDataSource
import com.example.core.datasources.RequestTimeDataSource
import com.example.core.domain.models.BitcoinUsdRate
import com.github.michaelbull.result.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

interface BitcoinRateRepository {
    suspend fun getBitcoinUsdRate(): Result<BitcoinUsdRate, Exception>
}

class BitcoinRateRepositoryImpl(
    private val remoteRateDS: BitcoinRateDataSource,
    private val localRateDS: BitcoinRateDataSource,
    private val localRequestTimeDS: RequestTimeDataSource,
    private val threshold: Long = 0,
) : BitcoinRateRepository {
    override suspend fun getBitcoinUsdRate() = withContext(Dispatchers.IO) {
        val lastUpdated = localRequestTimeDS.getLastRequestTime()
        /*
            TODO("discuss")
            not clear how should our system behave on the case, when we get error from
            the network on get currency method
                - whether we should use cached value, if exists, or not
                - how ui should react to this type of error - should it deny
                  refill requests or not
         */
        print(1)
        if (lastUpdated is Ok && Date().time - lastUpdated.value <= threshold) {
            localRateDS.getBitcoinRate().orElse { getApiRate() }
        } else {
            getApiRate().orElse { localRateDS.getBitcoinRate() }
        }
    }

    private suspend fun getApiRate() = remoteRateDS
        .getBitcoinRate()
        .onSuccess { rate ->
            localRequestTimeDS.saveLastRequestTime(Date().time)
            localRateDS.saveBitcoinRate(rate)
        }.onFailure {
            localRequestTimeDS.saveLastRequestTime(RequestTimeDataSource.DEFAULT_LAST_UPDATE)
        }
}
