package com.example.cryptomobileapp.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptomobileapp.R
import com.example.cryptomobileapp.databinding.FragmentAvalableCryptoBinding
import com.example.cryptomobileapp.models.CryptoDto
import com.example.cryptomobileapp.models.RetrofitInstance
import com.example.cryptomobileapp.viewModels.AvalableCryptoViewModel
import com.example.cryptomobileapp.viewModels.TAG
import kotlinx.coroutines.launch
import java.lang.Exception
class AvalableCrypto : Fragment(R.layout.fragment_avalable_crypto) {

    private lateinit var viewModel: AvalableCryptoViewModel
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
        viewModel = ViewModelProvider(this).get(AvalableCryptoViewModel::class.java)

        viewModel.getCryptos()
        binding.buttonRefresh.setOnClickListener {
            Refresh()
        }

        lifecycleScope.launch {
            viewModel.dataFlow.collect{ cryptos ->
                Log.d(TAG,"GOT: ${cryptos}")
                run {
                    initializeRecyclerView(cryptos)
                }
            }
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
        viewModel.getCryptos()
    }

}