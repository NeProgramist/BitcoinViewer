package com.example.data.framework.local.transactions

import androidx.paging.PagingSource
import androidx.paging.PagingState

class TransactionsPagingSource(
    private val transactionsDao: TransactionsDao,
): PagingSource<Int, TransactionEntity>() {
    override fun getRefreshKey(state: PagingState<Int, TransactionEntity>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TransactionEntity> {
        val position = params.key ?: DEFAULT_PAGE_VALUE
        val data = transactionsDao.

        return
    }

    companion object {
        const val DEFAULT_PAGE_VALUE = 0
        const val PAGE_SIZE = 20
    }
}
