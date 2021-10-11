package com.example.bitcoinviewer.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.core.domain.models.BitcoinUsdRate
import com.example.core.domain.repositories.BitcoinRateRepository
import com.example.data.framework.local.transactions.TransactionsLocalDataSource
import com.github.michaelbull.result.fold
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransactionsViewModel(
    private val transactionsDS: TransactionsLocalDataSource,
    private val rateRepository: BitcoinRateRepository,
) : ViewModel() {
    private val _rate = MutableLiveData<BitcoinUsdRate>()
    val rate: LiveData<BitcoinUsdRate> get() = _rate

    private val _rateException = MutableLiveData<Exception>()
    val rateException: LiveData<Exception> get() = _rateException

    val transactions: StateFlow<PagingData<TransactionsAdapterModel>> = Pager(
        PagingConfig(
            pageSize = 20,
            prefetchDistance = 40,
            maxSize = 100,
        )
    ) { transactionsDS.getTransactions() }.flow
        .map { pagingData -> pagingData.map { it.toTransactionsUiModel() } }
        .map { pagingData ->
            pagingData.insertSeparators { before, after ->
                // we can combine two branches into one, but it would be really hard to understand
                return@insertSeparators when {
                    // first item
                    before == null && after != null -> TransactionsAdapterModel.Header(after.date)
                    // date changed
                    before != null && after != null && after.date != before.date -> {
                        TransactionsAdapterModel.Header(after.date)
                    }
                    else -> null
                }
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun getBitcoinUsdRate() {
        viewModelScope.launch {
            rateRepository.getBitcoinUsdRate().fold(_rate::postValue, _rateException::postValue)
        }
    }
}
