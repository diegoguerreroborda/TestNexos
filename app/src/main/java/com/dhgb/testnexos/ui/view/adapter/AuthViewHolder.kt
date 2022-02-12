package com.dhgb.testnexos.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity
import com.dhgb.testnexos.databinding.ItemAuthBinding

class AuthViewHolder(
    view: View,
    private val itemClickListener: AuthAdapter.OnItemClickListener
): RecyclerView.ViewHolder(view) {

    private val binding = ItemAuthBinding.bind(view)

    fun bind(auth:AuthenticationEntity){
        binding.tvReceiptId.text = auth.receiptId
        if(auth.statusCode == "00") {
            binding.ivAccept.visibility = View.VISIBLE
        }
        binding.tvStatusDescription.text = "${auth.statusDescription}"

        binding.root.setOnClickListener{
            itemClickListener.onItemClick(auth)
        }
    }
}