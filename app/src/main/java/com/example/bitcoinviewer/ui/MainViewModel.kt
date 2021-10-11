package com.example.bitcoinviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.models.BitcoinUsdRate
import com.example.core.domain.repositories.BitcoinRateRepository
import com.github.michaelbull.result.fold
import kotlinx.coroutines.launch

class MainViewModel(
    private val rateRepository: BitcoinRateRepository,
) : ViewModel() {
    private val _rate = MutableLiveData<BitcoinUsdRate>()
    val rate: LiveData<BitcoinUsdRate> get() = _rate

    private val _rateException = MutableLiveData<Exception>()
    val rateException: LiveData<Exception> get() = _rateException

    fun getBitcoinUsdRate() {
        viewModelScope.launch {
            rateRepository.getBitcoinUsdRate().fold(_rate::postValue, _rateException::postValue)
        }
    }
}
