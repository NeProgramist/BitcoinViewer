package com.example.data.framework.local.transactions

import android.util.Log
import androidx.paging.PagingSource
import com.example.core.domain.models.Transaction
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

/*
    for most of the implementations of ds I have interfaces in the core-layer, but in the case of
    paging lib from google - I can't do that. They have their own model PagingSource and I think
    it shouldn't appear in the android-free core module

    https://github.com/android/architecture-components-samples/blob/main/PagingSample/app/src/main/java/paging/android/example/com/pagingsample/CheeseViewModel.kt

    and there is also a problem with mapping to the domain model in the data source, because
    we can map PagingData, but not a PagingSource by itself

    I've made up a small research and define that it's not only my problem
    https://stackoverflow.com/questions/50548387/android-paging-library-with-clean-architecture
*/
class TransactionsLocalDataSource(
    private val transactionsDao: TransactionsDao,
) {
    fun getTransactions(): PagingSource<Int, TransactionEntity> {
        return transactionsDao.getTransactions()
    }

    suspend fun insertTransaction(transaction: Transaction): Result<Unit, Exception> = try {
        transactionsDao.insert(transaction.toTransactionEntity())
        Ok(Unit)
    } catch (e: Exception) {
        /*
             In bigger cases I prefer to have a wrapper over kotlin Exception, like DomainException,
             HttpException, UnexpectedException and so on
             but in this case I think it's over engineering
         */
        Log.e(this::class.java.simpleName, e.message ?: "No error message")
        Err(e)
    }
}
