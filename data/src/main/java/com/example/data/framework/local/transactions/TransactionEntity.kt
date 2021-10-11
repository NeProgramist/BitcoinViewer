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

fun TransactionEntity.toTransaction() = Transaction(amount, type, date)
fun Transaction.toTransactionEntity() = TransactionEntity(
    id = UUID.randomUUID(),
    amount = amount,
    type = type,
    date = date,
)
