package com.dhgb.testnexos.core

import com.dhgb.testnexos.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper {

    fun getRetrofit(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .validateEagerly(true)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()
}