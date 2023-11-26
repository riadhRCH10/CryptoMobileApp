package com.example.cryptomobileapp.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptomobileapp.R
import com.example.cryptomobileapp.databinding.CryptoItemBinding
import com.example.cryptomobileapp.models.CryptoDto
import com.example.cryptomobileapp.viewModels.TAG

class CryptoAdapter(
    var cryptos: List<CryptoDto>
) : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    inner class CryptoViewHolder(val binding: CryptoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder( CryptoItemBinding.inflate( LayoutInflater.from(parent.context), parent, false ))
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.binding.apply {
            tvCryptoName.text = cryptos[position].name
            tvCryptoCount.text = "${cryptos[position].rates.size} Rates"
            tvProvider.text = "Provided by ${cryptos[position].provider}"

            rvAvalableCryptoList.adapter = RatesAdapter(cryptos[position].rates)
            rvAvalableCryptoList.layoutManager = LinearLayoutManager(root.context)

            cardHeader.setOnClickListener {
                val visible = if (rvAvalableCryptoList.isVisible) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
                Log.d(TAG,"updating visibility to ${visible}")
                rvAvalableCryptoList.visibility = visible
            }

        }
    }

    override fun getItemCount() = cryptos.size
}