package com.example.data.framework.di

import com.example.core.datasources.BitcoinRateDataSource
import com.example.core.datasources.RequestTimeDataSource
import com.example.core.di.local
import com.example.core.di.remote
import com.example.data.framework.local.rate.BitcoinRateLocalDataSource
import com.example.data.framework.local.PreferenceManager
import com.example.data.framework.local.rate.RequestTimeRemoteDataSource
import com.example.data.framework.remote.BitcoinRateApi
import com.example.data.framework.remote.BitcoinRateRemoteDataSource
import com.example.data.framework.remote.provideApi
import org.koin.dsl.module

val dataModule = module {
    single { provideApi(BitcoinRateApi::class.java, "https://api.coindesk.com/v1/") }
    single { PreferenceManager(get()) }

    single<BitcoinRateDataSource>(local) { BitcoinRateLocalDataSource(get()) }
    single<BitcoinRateDataSource>(remote) { BitcoinRateRemoteDataSource(get()) }
    single<RequestTimeDataSource> { RequestTimeRemoteDataSource(get()) }
}
