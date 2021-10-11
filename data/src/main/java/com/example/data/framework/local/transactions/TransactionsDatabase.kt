package com.example.data.framework.local.transactions

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(version = 1, entities = [TransactionEntity::class], exportSchema = false)
@TypeConverters(DateConverters::class, TransactionTypeConverters::class, UUIDConverter::class)
abstract class TransactionsDatabase : RoomDatabase() {
    abstract fun transactionsDao(): TransactionsDao
}
