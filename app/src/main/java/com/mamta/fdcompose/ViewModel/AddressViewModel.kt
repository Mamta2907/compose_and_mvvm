package com.mamta.fdcompose.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mamta.fdcompose.ApiConfig.RetrofitInstance
import com.mamta.fdcompose.Model.AddressResponseData
import com.mamta.fdcompose.Model.CreateAddressBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressViewModel() : ViewModel() {

    private val _addressResponse = MutableStateFlow<AddressResponseData?>(null)
    val addressResponse = _addressResponse.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

     fun createAddress(addressBody: CreateAddressBody) {

        viewModelScope.launch {
            try {
               // _isLoading.value = true
                val response = RetrofitInstance.apiService.createAddress(addressBody)
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _addressResponse.value = response.body()
                } else {
                   // _isLoading.value = false
                    _errorMessage.value = "Error: ${response.body()}"
                }
            } catch (e: Exception) {
                //_isLoading.value = false
                _errorMessage.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}