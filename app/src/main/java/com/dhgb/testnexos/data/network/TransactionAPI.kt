package com.dhgb.testnexos.data.network

import com.dhgb.testnexos.data.model.AuthenticationModel
import com.dhgb.testnexos.data.model.TransactionModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface TransactionAPI {

    @POST("authorization")
    suspend fun authTransaction(@HeaderMap headers: HashMap<String, String>, @Body requestBody: TransactionModel)
    : Response<AuthenticationModel>

}