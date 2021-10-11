package com.example.bitcoinviewer.ui.transactions

import com.example.core.domain.models.TransactionType
import com.example.data.framework.local.transactions.TransactionEntity
import java.util.*

sealed class TransactionsAdapterModel {
    data class Header(val title: String): TransactionsAdapterModel()
    data class Transaction(
        val id: UUID,
        val amount: String,
        val type: TransactionType,
        val date: String,
    ): TransactionsAdapterModel()
}

fun TransactionEntity.toTransactionsUiModel() = TransactionsAdapterModel.Transaction(
    id = id,
    amount = amount.toString(),
    date = shortDateTimeString(date),
    type = type,
)

fun shortDateTimeString(
    date: Date,
    locale: Locale = Locale.getDefault(),
) = "d MMM yyyy".format(date).toLowerCase(locale)
