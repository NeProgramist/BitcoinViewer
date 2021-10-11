package com.example.data.framework.local.transactions

import androidx.room.Database

@Database(version = 1, entities = [TransactionEntity::class], exportSchema = false)
abstract class TransactionsDatabase {
    abstract fun getDao(): TransactionsDao
}
