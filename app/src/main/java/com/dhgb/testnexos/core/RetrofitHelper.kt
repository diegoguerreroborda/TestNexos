package com.dhgb.testnexos.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://192.168.20.22:8080/api/payments/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}