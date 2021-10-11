package com.example.core.datasources

import com.github.michaelbull.result.Result

interface BalanceDataSource {
    suspend fun getBalance(): Result<Int, Exception>
    suspend fun updateBalance(amount: Int): Result<Unit, Exception>
}
