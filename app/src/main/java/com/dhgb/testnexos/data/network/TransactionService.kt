package com.dhgb.testnexos.data.network

import com.dhgb.testnexos.core.RetrofitHelper
import com.dhgb.testnexos.data.model.AuthenticationModel
import com.dhgb.testnexos.data.model.TransactionModel
import retrofit2.Response

class TransactionService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun authTransaction(headers: HashMap<String, String>, transaction: TransactionModel): Response<AuthenticationModel> {
        return retrofit.create(TransactionAPI::class.java).authTransaction(headers, transaction)
    }
}