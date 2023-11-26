package com.example.cryptomobileapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptomobileapp.models.CryptoDto
import com.example.cryptomobileapp.models.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
const val TAG = "API_CALL"

class AvalableCryptoViewModel : ViewModel() {

    private lateinit var cryptos : List<CryptoDto>
    private val _dataFlow = MutableStateFlow<List<CryptoDto>>(emptyList())
    val dataFlow: StateFlow<List<CryptoDto>> = _dataFlow.asStateFlow()

    fun getCryptos() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCryptos()
                if (response.isSuccessful){
                    Log.d(TAG,"success!! : ${response.body()}")
                    _dataFlow.emit(response.body()!!)
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