package com.example.cryptomobileapp.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptomobileapp.R
import com.example.cryptomobileapp.databinding.FragmentAvalableCryptoBinding
import com.example.cryptomobileapp.models.CryptoDto
import com.example.cryptomobileapp.viewModels.AvalableCryptoViewModel
import com.example.cryptomobileapp.viewModels.TAG
import kotlinx.coroutines.launch
import androidx.fragment.app.viewModels
class AvalableCrypto : Fragment(R.layout.fragment_avalable_crypto) {

    private val viewModel: AvalableCryptoViewModel by viewModels()
    private lateinit var binding: FragmentAvalableCryptoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvalableCryptoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launch {
            viewModel.dataFlow.collect{ cryptos ->
                Log.d(TAG,"GOT: ${cryptos}")
                binding.progressBar.visibility = View.GONE
                Toast.makeText(context, "revieved ${cryptos.size} cryptos", Toast.LENGTH_SHORT).show()
                initializeRecyclerView(cryptos)
            }
        }

        binding.buttonRefresh.setOnClickListener {
            Refresh()
        }

    }

    fun initializeRecyclerView(cryptos: List<CryptoDto>) {
        val adapter = CryptoAdapter(cryptos)
        binding.apply {
            rvCryptos.adapter = adapter
            rvCryptos.layoutManager = LinearLayoutManager(context)
        }
    }

    fun Refresh() {
        Log.d(TAG, "refreshing!")
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getCryptos()
    }

}