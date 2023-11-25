package com.example.cryptomobileapp.models

data class CryptoDto(
    val __v: Int,
    val _id: String,
    val name: String,
    val provider: String,
    val rates: List<Rate>
)