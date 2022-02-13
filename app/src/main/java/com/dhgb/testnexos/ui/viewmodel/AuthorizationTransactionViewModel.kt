package com.dhgb.testnexos.ui.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgb.testnexos.data.database.AuthenticationDb
import com.dhgb.testnexos.data.database.dao.AuthDao
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity
import com.dhgb.testnexos.data.model.TransactionModel
import com.dhgb.testnexos.data.network.TransactionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Base64
import kotlin.collections.HashMap

class AuthorizationTransactionViewModel: ViewModel() {

    private val api = TransactionService()
    private lateinit var context: Context
    private lateinit var db: AuthDao

    val mutableStatusCode = MutableLiveData<Int>()

    fun onCreate(contextC: Context) {
        context = contextC
        db = AuthenticationDb.DatabaseProvider.getDataBase(context).authDao()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleHeaders(commerceCode: String, terminalCode: String): HashMap<String,String>{
        val headers = HashMap<String, String>()
        headers[AUTHORIZATION] = "Basic ${getBase64(commerceCode, terminalCode)}"
        headers[CONTENT_TYPE] = CONTENT_TYPE_RESULT
        return headers
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun uploadTransaction(id: Int, commerceCode: String, terminalCode: String, amount: String, card: String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.authTransaction(
                handleHeaders(commerceCode, terminalCode),
                TransactionModel(id.toString(), commerceCode, terminalCode,
                amount, card))
            if(response != null){
                mutableStatusCode.postValue(response.code())
                if(response.code() == STATUS_ACCEPTED){
                    val authEntity = AuthenticationEntity(response.body()!!.receiptId, response.body()!!.rrn,
                        response.body()!!.statusCode, response.body()!!.statusDescription,
                        id.toString(), commerceCode, terminalCode, amount, card)
                    db.insertApproveAuth(authEntity)
                }
            }else {
                mutableStatusCode.postValue(-1)
            }

        }
    }

    private fun getTypeStatus(statusCode: Int): String{
         return when(statusCode) {
            STATUS_ACCEPTED -> "Authorization accepted"
            STATUS_BAD_REQUEST -> "Bad request"
            STATUS_UNAUTHORIZED -> "Sin autorización"
            else -> "Error inesperado"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getBase64(commerceCode: String, terminalCode: String): String {
        val joinText = "$commerceCode$terminalCode"
        return Base64.getEncoder().encodeToString(joinText.toByteArray())
    }

    companion object {
        private const val CONTENT_TYPE = "Content-Type"
        private const val AUTHORIZATION = "Authorization"
        private const val CONTENT_TYPE_RESULT = "application/json"

        private const val STATUS_ACCEPTED = 202
        private const val STATUS_BAD_REQUEST = 400
        private const val STATUS_UNAUTHORIZED = 401
    }
}