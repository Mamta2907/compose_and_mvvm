package com.mamta.fdcompose.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mamta.fdcompose.ApiConfig.ApiService
import com.mamta.fdcompose.ApiConfig.RetrofitInstance
import com.mamta.fdcompose.Model.Product
import com.mamta.fdcompose.Model.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val _scrapProduct = MutableStateFlow<List<ProductModel>>(emptyList())
    val scrapProductList: StateFlow<List<ProductModel>> = _scrapProduct

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _productResponse = MutableStateFlow<List<Product>>(emptyList())
    val productResponse: StateFlow<List<Product>> get() = _productResponse


    suspend fun getProductList() {
        viewModelScope.launch {
            try {
                val productList = RetrofitInstance.apiService.getProducts().products
                _productResponse.value = productList
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown Error"
            }
        }
    }

    fun getScrapProductList() {
        RetrofitInstance.apiService.getScrapProduct()
            .enqueue(object : Callback<List<List<ProductModel>>?> {
                override fun onResponse(
                    call: Call<List<List<ProductModel>>?>,
                    response: Response<List<List<ProductModel>>?>
                ) {
                    if (response.isSuccessful) {

                        //_scrapProduct.value = response.body()!!
                        response.body()?.let {
                            _scrapProduct.value = it[0]
                        }

                    } else {
                        _errorMessage.value = "Response Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<List<List<ProductModel>>?>, t: Throwable) {
                    _errorMessage.value = "Error: ${t.localizedMessage}"
                }
            })
    }

    fun getScrapProduct() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.getScrapProducts()
                if (response.isSuccessful) {

                    response.body()?.let {
                        _scrapProduct.value = it[0]
                    }

                } else {
                    _errorMessage.value = "Response Error: ${response.code()}"
                }

            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}