package com.example.bitcoinviewer.di

import com.example.bitcoinviewer.ui.MainViewModel
import com.example.bitcoinviewer.ui.add_transaction.AddTransactionsViewModel
import com.example.bitcoinviewer.ui.transactions.TransactionsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { TransactionsViewModel(get(), get()) }
    viewModel { AddTransactionsViewModel(get(), get()) }
}
