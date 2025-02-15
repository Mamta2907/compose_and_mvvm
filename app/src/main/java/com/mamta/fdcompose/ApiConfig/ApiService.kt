package com.mamta.fdcompose.ApiConfig

import com.mamta.fdcompose.Model.AddressResponseData
import com.mamta.fdcompose.Model.CreateAddressBody
import com.mamta.fdcompose.Model.ProductModel
import com.mamta.fdcompose.Model.ProductResponse
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //https://dummyjson.com/products

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("allProducts")
    fun getScrapProducts(): Response<List<List<ProductModel>>>

    @GET("allProducts")
    fun getScrapProduct(): Call<List<List<ProductModel>>>

    @POST("createAddress")
    suspend fun createAddress(@Body createAddressBody: CreateAddressBody):Response<AddressResponseData>


    /*companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://dummyjson.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }*/

}
