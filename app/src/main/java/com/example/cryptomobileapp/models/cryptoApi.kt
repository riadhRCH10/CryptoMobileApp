package com.example.cryptomobileapp.models

import retrofit2.Response
import retrofit2.http.GET

interface cryptoApi {

    @GET("/crypto")
    suspend fun getCryptos() : Response<List<CryptoDto>>

}