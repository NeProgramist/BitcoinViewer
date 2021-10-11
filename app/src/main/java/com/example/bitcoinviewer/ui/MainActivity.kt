package com.example.bitcoinviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bitcoinviewer.R
import com.example.bitcoinviewer.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.rate.observe(this) {
            binding.text.text = it.toString()
        }

        viewModel.rateException.observe(this) {
            binding.text.text = it.message
        }

        viewModel.getBitcoinUsdRate()
    }
}
