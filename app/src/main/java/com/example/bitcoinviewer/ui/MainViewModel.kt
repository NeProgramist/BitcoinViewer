package com.example.bitcoinviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoinviewer.routing.AppStage
import com.example.core.domain.models.BitcoinUsdRate
import com.example.core.domain.repositories.BitcoinRateRepository
import com.github.michaelbull.result.fold
import kotlinx.coroutines.launch

class MainViewModel(
    private val rateRepository: BitcoinRateRepository,
) : ViewModel() {
    val stage = MutableLiveData(AppStage.Transactions)
    var prev: AppStage? = null

    fun changeStage(newStage: AppStage) {
        val current = stage.value
        if (current != newStage) {
            prev = current
            stage.value = newStage
        }
    }

    fun back() {
        val next = when (stage.value) {
            AppStage.Refill, AppStage.AddTransaction -> AppStage.Transactions
            else -> AppStage.Cancel
        }
        changeStage(next)
    }


}
