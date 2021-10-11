package com.example.data.framework.local.transactions

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [TransactionEntity::class], exportSchema = false)
abstract class TransactionsDatabase : RoomDatabase() {
    abstract fun transactionsDao(): TransactionsDao
}
