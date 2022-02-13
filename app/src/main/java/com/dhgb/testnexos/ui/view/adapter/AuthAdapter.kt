package com.dhgb.testnexos.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhgb.testnexos.R
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity

class AuthAdapter(
    private var authList: List<AuthenticationEntity>,
    private val itemClickListener: OnItemClickListener
)
    : RecyclerView.Adapter<AuthViewHolder>(){

    interface OnItemClickListener{
        fun onItemClick(authenticationEntity: AuthenticationEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AuthViewHolder(
            layoutInflater.inflate(R.layout.item_auth, parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: AuthViewHolder, position: Int) {
        val item = authList[position]
        if(item != null){
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int = authList.size

    fun updateList(list: List<AuthenticationEntity>) {
        authList = list
        notifyDataSetChanged()
    }

    fun filter(text: String?) {
        val temp: MutableList<AuthViewHolder> = ArrayList()
        for (d in authList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.receiptId.contains("$text")) {
//                temp.add(d)
            }
        }
        //update recyclerview
    }
}