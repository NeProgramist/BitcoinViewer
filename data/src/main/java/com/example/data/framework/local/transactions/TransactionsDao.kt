package com.example.data.framework.local.transactions

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TransactionsDao {
    @Query("select * from transactions")
    suspend fun getTransactions(): PagingSource<Int, TransactionEntity>
}
