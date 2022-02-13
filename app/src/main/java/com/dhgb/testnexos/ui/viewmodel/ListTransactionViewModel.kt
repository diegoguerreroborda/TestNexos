package com.dhgb.testnexos.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgb.testnexos.data.database.AuthenticationDb
import com.dhgb.testnexos.data.database.dao.AuthDao
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListTransactionViewModel : ViewModel() {

    private lateinit var context: Context
    private lateinit var db: AuthDao

    val mutableListAuth = MutableLiveData<List<AuthenticationEntity>>()
    lateinit var listAuth: List<AuthenticationEntity>

    fun onCreate(contextC: Context) {
        context = contextC
        db = AuthenticationDb.DatabaseProvider.getDataBase(context).authDao()
        getAuthList()
    }

    private fun getAuthList() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = db.getApproveAuth()
            listAuth = list
            mutableListAuth.postValue(list)
        }
    }

    fun getAuthByReceiptId(text: String) {
        if(text.isNotEmpty()){
            val auxListAuth: MutableList<AuthenticationEntity> = ArrayList()
            for (d in listAuth) {
                if (d.receiptId.contains("$text")) {
                    auxListAuth.add(d)
                }
            }
            mutableListAuth.postValue(auxListAuth)
        }else {
            mutableListAuth.postValue(listAuth)
        }
    }
}