package com.example.bitcoinviewer.di

import android.app.Application
import com.example.core.di.coreModule
import com.example.data.framework.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)

            modules(appModule)
            modules(dataModule)
            modules(coreModule)
        }
    }
}
