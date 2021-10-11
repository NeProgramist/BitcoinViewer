package com.example.data.framework.local.transactions

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.domain.models.TransactionType
import java.util.*

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: UUID,
    val type: TransactionType,
    val amount: Int,
    val date: Date,
)
