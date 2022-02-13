package com.dhgb.testnexos.ui.view.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.dhgb.testnexos.databinding.FragmentTransactionDetailBinding
import com.dhgb.testnexos.ui.viewmodel.TransactionDetailViewModel

class TransactionDetailFragment : Fragment() {

    private var _binding: FragmentTransactionDetailBinding? = null
    private val binding get() = _binding!!

    private val transactionViewModel: TransactionDetailViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTransactionDetailBinding.inflate(inflater, container, false)

        transactionViewModel.onCreate(binding.root.context)

        binding.ivBack.setOnClickListener{
            requireActivity().onBackPressed()
        }

        arguments?.let {
            val transaction = TransactionDetailFragmentArgs.fromBundle(it).transactionDetail
            binding.tvId.text = "#${transaction.id}"
            binding.tvReceiptId.text = "Receipt Id: \n${transaction.receiptId}"
            binding.tvRrn.text = "RRN : \n${transaction.rrn}"
            binding.tvCommerceCode.text = "Commerce Code: \n${transaction.commerceCode}"
            binding.tvTerminalCode.text = "Terminal Code: \n${transaction.terminalCode}"
            binding.tvStatusCode.text = "Status Code: \n${transaction.statusCode}"
            binding.tvAmount.text = "Amount: \n${transaction.amount}"
            binding.tvCard.text = "Card: \n${transaction.card}"

            binding.ivDelete.setOnClickListener{
                transactionViewModel.deleteAuth(transaction)
            }

            transactionViewModel.goBack.observe(viewLifecycleOwner, {
                requireActivity().onBackPressed()
            })

            transactionViewModel.resServer.observe(viewLifecycleOwner, {
                showToast(it)
            })
        }

        return binding.root
    }

    private fun showToast(message: String){
        Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
    }
}