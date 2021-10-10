package com.example.core.di

import com.example.core.domain.repositories.BitcoinRateRepository
import com.example.core.domain.repositories.BitcoinRateRepositoryImpl
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val coreModule = module {
    single<BitcoinRateRepository> {
        BitcoinRateRepositoryImpl(get(remote), get(local), get(), TimeUnit.HOURS.toMillis(1))
    }
}
