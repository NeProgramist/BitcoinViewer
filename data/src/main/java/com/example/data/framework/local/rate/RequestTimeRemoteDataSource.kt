package com.example.data.framework.local.rate

import com.example.core.datasources.RequestTimeDataSource
import com.example.core.datasources.RequestTimeDataSource.Companion.DEFAULT_LAST_UPDATE
import com.example.data.framework.local.PreferenceManager
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class RequestTimeRemoteDataSource(
    private val prefManager: PreferenceManager,
) : RequestTimeDataSource {
    private var lastUpdate = DEFAULT_LAST_UPDATE

    override suspend fun getLastRequestTime(): Result<Long, Exception> {
        return if (lastUpdate != DEFAULT_LAST_UPDATE) {
            Ok(lastUpdate)
        } else {
            val time = prefManager.getLong(PREF_LAST_UPDATE, DEFAULT_LAST_UPDATE)

            if (time == DEFAULT_LAST_UPDATE) {
                Err(IllegalStateException("No saved request time"))
            } else {
                lastUpdate = time
                Ok(time)
            }
        }
    }

    override suspend fun saveLastRequestTime(time: Long): Result<Unit, Exception> {
        prefManager.putLong(PREF_LAST_UPDATE, time)
        return Ok(Unit)
    }

    companion object {
        const val PREF_LAST_UPDATE = "PREF_LAST_UPDATE"
    }
}
