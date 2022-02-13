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
import com.dhgb.testnexos.data.network.TransactionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TransactionDetailViewModel: ViewModel() {

    private val api = TransactionService()
    private lateinit var context: Context
    private lateinit var db: AuthDao

    val goBack = MutableLiveData<Boolean>()

    fun onCreate(contextC: Context) {
        context = contextC
        db = AuthenticationDb.DatabaseProvider.getDataBase(context).authDao()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deleteAuth(auth: AuthenticationEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("SIN_INTERNET", "Annulment viewmodel")
            val response = api.annulmentTransaction(handleHeaders(auth.commerceCode, auth.terminalCode), auth)
            if(response != null){
                db.deleteApproveAuth(auth)
                goBack.postValue(true)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getBase64(commerceCode: String, terminalCode: String): String {
        val joinText = "$commerceCode$terminalCode"
        return Base64.getEncoder().encodeToString(joinText.toByteArray())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleHeaders(commerceCode: String, terminalCode: String): HashMap<String, String> {
        val headers = HashMap<String, String>()
        headers[AUTHORIZATION] = "Basic ${getBase64(commerceCode, terminalCode)}"
        headers[CONTENT_TYPE] = CONTENT_TYPE_RESULT
        return headers
    }

    companion object {
        private const val CONTENT_TYPE = "Content-Type"
        private const val AUTHORIZATION = "Authorization"
        private const val CONTENT_TYPE_RESULT = "application/json"
    }
}