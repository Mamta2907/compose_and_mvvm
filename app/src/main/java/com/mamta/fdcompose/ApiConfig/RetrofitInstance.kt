package com.mamta.fdcompose.ApiConfig

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //private const val baseUrl = "https://rndtd.com/demos/scrapbazar/api/"

    private const val BASE_URL = "https://rndtd.com/demos/scrapbazar/api/"
    val gson = GsonBuilder().setLenient().create()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}