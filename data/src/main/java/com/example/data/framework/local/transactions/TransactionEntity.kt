package com.example.data.framework.local.transactions

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.domain.models.Transaction
import com.example.core.domain.models.TransactionType
import java.util.*

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: UUID,
    val amount: Int,
    val type: TransactionType,
    val date: Date,
)

fun Transaction.toTransactionEntity() = TransactionEntity(id, amount, type, date)
