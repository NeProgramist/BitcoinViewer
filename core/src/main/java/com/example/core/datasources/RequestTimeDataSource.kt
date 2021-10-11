package com.example.core.datasources

import com.github.michaelbull.result.Result

interface RequestTimeDataSource {
    suspend fun getLastRequestTime(): Result<Long, Exception>
    suspend fun saveLastRequestTime(time: Long): Result<Unit, Exception>

    companion object {
        const val DEFAULT_LAST_UPDATE = -1L
    }
}
