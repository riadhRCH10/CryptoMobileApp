package com.example.cryptomobileapp.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val baseUrl = "http://192.168.1.3:3000/crypto/"

    val api : cryptoApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(cryptoApi::class.java)
    }
}