package com.example.bitcoinviewer.ui.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoinviewer.databinding.RecyclerItemHeaderBinding
import com.example.bitcoinviewer.databinding.RecyclerItemTransactionBinding
import com.example.bitcoinviewer.ui.transactions.TransactionsAdapterModel.*

typealias TransactionsPagingAdapter = PagingDataAdapter<TransactionsAdapterModel, RecyclerView.ViewHolder>

val unsupportedViewTypeException: Nothing = error("Unsupported view type")

class TransactionsAdapter : PagingDataAdapter<TransactionsAdapterModel, RecyclerView.ViewHolder>(TransactionsAdapterDiffUtils) {

    override fun getItemViewType(position: Int) = when(getItem(position)) {
        is Header -> HEADER_VIEW_TYPE
        is Transaction -> TRANSACTION_VIEW_TYPE
        else -> unsupportedViewTypeException
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            HEADER_VIEW_TYPE -> {
                val binding = RecyclerItemHeaderBinding.inflate(
                    layoutInflater,
                    parent,
                    false,
                )

                HeaderViewHolder(binding)
            }
            TRANSACTION_VIEW_TYPE -> {
                val binding = RecyclerItemTransactionBinding.inflate(
                    layoutInflater,
                    parent,
                    false,
                )

                TransactionViewHolder(binding)
            }
            else -> unsupportedViewTypeException
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when {
            holder is HeaderViewHolder && item is Header? -> holder.bind(item)
            holder is TransactionViewHolder && item is Transaction? -> holder.bind(item)
        }
    }

    inner class HeaderViewHolder(
        private val binding: RecyclerItemHeaderBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Header?) {
            binding.date.text = item?.title
        }
    }

    inner class TransactionViewHolder(
        private val binding: RecyclerItemTransactionBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction?) = with(binding) {
            amount.text = item?.amount
            type.text = item?.type?.toString()
            date.text = item?.date
        }
    }

    companion object {
        const val HEADER_VIEW_TYPE = 0
        const val TRANSACTION_VIEW_TYPE = 1
    }
}

private object TransactionsAdapterDiffUtils : DiffUtil.ItemCallback<TransactionsAdapterModel>() {
    override fun areItemsTheSame(
        oldItem: TransactionsAdapterModel,
        newItem: TransactionsAdapterModel
    ) = when {
        oldItem is Header && newItem is Header -> oldItem.title == newItem.title
        oldItem is Transaction && newItem is Transaction -> oldItem.id == newItem.id
        else -> false
    }

    override fun areContentsTheSame(
        oldItem: TransactionsAdapterModel,
        newItem: TransactionsAdapterModel
    ) = when {
        oldItem is Header && newItem is Header -> oldItem.title == newItem.title
        oldItem is Transaction && newItem is Transaction -> oldItem == newItem
        else -> false
    }
}
