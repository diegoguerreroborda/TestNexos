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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ListTransactionViewModel: ViewModel() {

    private lateinit var context: Context
    private lateinit var db: AuthDao

    val mutableListAuth = MutableLiveData<List<AuthenticationEntity>>()

    val isVoidList = MutableLiveData<Boolean>()

    fun onCreate(contextC: Context) {
        context = contextC
        db = AuthenticationDb.DatabaseProvider.getDataBase(context).authDao()
        getAuthList()
    }

    fun getAuthList() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = db.getApproveAuth()
            Log.d("ROOOM", "$list")
            mutableListAuth.postValue(list)
        }
    }

    fun getAuthByReceiptId(receiptId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            if(db.getAuthByReceiptId(receiptId).isNotEmpty()){
                mutableListAuth.postValue(db.getAuthByReceiptId(receiptId))
            }else{
                isVoidList.postValue(true)
            }

        }
    }
}