package com.example.cryptomobileapp.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val baseUrl = "http://10.0.2.2:3000"

    val api : cryptoApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(cryptoApi::class.java)
    }
}