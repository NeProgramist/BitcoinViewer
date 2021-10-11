package com.example.data.framework.local.transactions

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionsDao {
    @Query("select * from transactions as t order by t.date desc")
    fun getTransactions(): PagingSource<Int, TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity)
}
