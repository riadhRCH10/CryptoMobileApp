package com.example.cryptomobileapp.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptomobileapp.R
import com.example.cryptomobileapp.databinding.CryptoItemBinding
import com.example.cryptomobileapp.databinding.RateItemBinding
import com.example.cryptomobileapp.models.Rate

class RatesAdapter(
    private val rates: List<Rate>
) : RecyclerView.Adapter<RatesAdapter.RateViewHolder>() {

    inner class RateViewHolder(val binding: RateItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder( RateItemBinding.inflate( LayoutInflater.from(parent.context), parent, false ))
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.binding.apply {
            tvCurrency.text = rates[position].currency
            tvValue.text = rates[position].value.toString()
        }
    }

    override fun getItemCount() = rates.size
}