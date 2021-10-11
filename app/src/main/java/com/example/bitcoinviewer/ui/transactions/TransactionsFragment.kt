package com.example.bitcoinviewer.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.bitcoinviewer.R
import com.example.bitcoinviewer.databinding.FragmentTransactionsBinding
import com.example.bitcoinviewer.routing.AppStage
import com.example.bitcoinviewer.ui.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TransactionsFragment : Fragment() {
    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!

    private val flowViewModel: MainViewModel by sharedViewModel()
    private val viewModel: TransactionsViewModel by viewModel()
    private val adapter: TransactionsAdapter by lazy {
        TransactionsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        transactions.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            whenStarted {
                viewModel.transactions.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }

        viewModel.rate.observe(viewLifecycleOwner) {
            rate.text = getString(R.string.rate_placeholder, it.rate)
        }

        viewModel.rateException.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }

        refill.setOnClickListener {
            flowViewModel.changeStage(AppStage.Refill)
        }

        addTransaction.setOnClickListener {
            flowViewModel.changeStage(AppStage.AddTransaction)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getBitcoinUsdRate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
