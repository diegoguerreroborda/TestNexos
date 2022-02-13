package com.dhgb.testnexos.data.network

import android.util.Log
import com.dhgb.testnexos.core.RetrofitHelper
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity
import com.dhgb.testnexos.data.model.AuthenticationModel
import com.dhgb.testnexos.data.model.ResponseAnnulment
import com.dhgb.testnexos.data.model.TransactionModel
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.UnknownHostException

class TransactionService {

    private val retrofit = RetrofitHelper.getRetrofit()?.create(TransactionAPI::class.java)

    suspend fun authTransaction(headers: HashMap<String, String>, transaction: TransactionModel
    ): Response<AuthenticationModel>? {
        return try {
            val res = retrofit?.authTransaction(headers, transaction)
            if(res?.isSuccessful == true){
                res
            }else {
                Log.d("SIN_INTERNET", "URL incorrecta")
                null
            }
        } catch (e: UnknownHostException) {
            Log.d("SIN_INTERNET", "Sin internet")
            null
        }
    }

    suspend fun annulmentTransaction(headers: HashMap<String, String>, transaction: AuthenticationEntity
    ): Response<ResponseAnnulment>? {
        return try{
            val res = retrofit?.annulmentTransaction(headers, transaction)
            if(res?.isSuccessful == true){
                res
            }else {
                null
            }
        } catch (e: UnknownHostException) {
            null
        }
    }
}