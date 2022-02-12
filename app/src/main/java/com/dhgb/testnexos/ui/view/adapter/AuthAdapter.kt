package com.dhgb.testnexos.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhgb.testnexos.R
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity

class AuthAdapter (
    private val authList: List<AuthenticationEntity>,
    private val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<AuthViewHolder>(){

    interface OnItemClickListener{
        fun onItemClick(authenticationEntity: AuthenticationEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AuthViewHolder(layoutInflater.inflate(R.layout.item_auth, parent, false), itemClickListener)
    }

    override fun onBindViewHolder(holder: AuthViewHolder, position: Int) {
        val item = authList[position]
        if(item != null){
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int = authList.size
}