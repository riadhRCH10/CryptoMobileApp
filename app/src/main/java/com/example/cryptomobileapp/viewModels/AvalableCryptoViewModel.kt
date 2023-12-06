package com.example.cryptomobileapp.viewModels

import android.util.Log
import android.widget.Toast
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
                if (response.isSuccessful) {
                    val cryptoList = response.body() ?: emptyList()
                    _dataFlow.emit(cryptoList)
                    Log.d(TAG,"success! ${cryptoList}")
                } else {
                    _dataFlow.emit(emptyList())
                    Log.d(TAG,"failed! ")
                }
            } catch (e: Exception) {
                _dataFlow.emit(emptyList())
                Log.d(TAG,"exception ${e}")
            }
        }
    }

}