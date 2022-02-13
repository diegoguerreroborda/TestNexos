package com.dhgb.testnexos.core

import com.dhgb.testnexos.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    fun getRetrofit(): Retrofit? {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .validateEagerly(true)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}