package com.example.bitcoinviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bitcoinviewer.R
import com.example.bitcoinviewer.databinding.ActivityMainBinding
import com.example.bitcoinviewer.routing.AppStage.*
import com.example.bitcoinviewer.routing.changeFragment
import com.example.bitcoinviewer.routing.getAnimationSet
import com.example.bitcoinviewer.ui.add_transaction.AddTransactionFragment
import com.example.bitcoinviewer.ui.transactions.TransactionsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.stage.observe(this) { stage ->
            when(stage) {
                Transactions -> changeFragment(TransactionsFragment())
                Refill -> TODO()
                AddTransaction -> changeFragment(AddTransactionFragment())
                Cancel -> finish()
            }
        }
    }

    override fun onBackPressed() {
        viewModel.changeStage(Cancel)
    }

    private fun changeFragment(fragment: Fragment) {
        val animSet = getAnimationSet(viewModel.stage.value, viewModel.prev)

        supportFragmentManager.changeFragment(
            fragment = fragment,
            container = R.id.container,
            backStack = false,
            animationSet = animSet,
        )
    }

}
