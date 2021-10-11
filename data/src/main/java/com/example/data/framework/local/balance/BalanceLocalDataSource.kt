package com.example.data.framework.local.balance

import com.example.core.datasources.BalanceDataSource
import com.example.data.framework.local.PreferenceManager
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class BalanceLocalDataSource(
    private val prefManager: PreferenceManager,
) : BalanceDataSource {
    private var balance = DEFAULT_BALANCE_VALUE

    override suspend fun getBalance(): Result<Int, Exception> {
        return if (balance != DEFAULT_BALANCE_VALUE) {
            Ok(balance)
        } else {
            val savedBalance = prefManager.getInt(PREF_BALANCE, DEFAULT_BALANCE_VALUE)

            if (savedBalance != DEFAULT_BALANCE_VALUE) {
                balance = savedBalance
                Ok(savedBalance)
            } else {
                balance = 0
                prefManager.putInt(PREF_BALANCE, 0)

                Ok(balance)
            }
        }
    }

    override suspend fun updateBalance(amount: Int): Result<Unit, Exception> {
        val newBalance = balance + amount
        return if (amount !in 0..Int.MAX_VALUE) {
            Err(Exception("Balance out of bound"))
        } else {
            balance = newBalance
            prefManager.putInt(PREF_BALANCE, newBalance)
            Ok(Unit)
        }
    }

    companion object {
        const val DEFAULT_BALANCE_VALUE = -1
        const val PREF_BALANCE = "PREF_BALANCE"
    }
}
