package com.example.core.domain.models

import java.util.*

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val amount: Int,
    val type: TransactionType,
    val date: Date,
)

enum class TransactionType {
    Groceries, Taxi, Electronics, Restaurant, Other
}
