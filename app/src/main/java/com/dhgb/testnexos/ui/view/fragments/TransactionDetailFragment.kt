package com.dhgb.testnexos.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhgb.testnexos.R
import com.dhgb.testnexos.databinding.FragmentTransactionDetailBinding

class TransactionDetailFragment : Fragment() {

    private var _binding: FragmentTransactionDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTransactionDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            val transaction = TransactionDetailFragmentArgs.fromBundle(it).transactionDetail
        }

        return binding.root
    }
}