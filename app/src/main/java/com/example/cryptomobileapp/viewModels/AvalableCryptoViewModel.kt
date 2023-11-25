package com.example.cryptomobileapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptomobileapp.models.CryptoDto
import com.example.cryptomobileapp.models.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception
const val TAG = "API_CALL"

class AvalableCryptoViewModel : ViewModel() {

    lateinit var cryptos : List<CryptoDto>

    fun getCryptos() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCryptos()
                if (response.isSuccessful){
                    Log.d(TAG,"success!! : ${response.body()}")
                    cryptos = response.body()!!
                }else {
                    Log.d(TAG,"unsuccessfull")
                }
            } catch (e: Exception){
                Log.d(TAG,"exception: ${e}")
            }

        }
    }

}