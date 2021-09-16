package com.example.healthSewa.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

//    private const val BASE_URL=  "http://localhost:90/"

//    private const val BASE_URL=  "http://10.0.2.2:90/"
   private const val BASE_URL=  "http://192.168.88.63:90/"
//    private const val BASE_URL=  "http://172.26.0.66:90/"


    var token :String?=null
    private val okHttp =  OkHttpClient.Builder()

    //Create retrofit Builder

    private val RetrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    //create retrofit instance

    private val retrofit = RetrofitBuilder.build()

    //generic Function

    fun <T> buildServices(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

}