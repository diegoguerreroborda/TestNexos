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
import androidx.lifecycle.Observer
import com.dhgb.testnexos.databinding.FragmentAuthorizationTransactionBinding
import com.dhgb.testnexos.ui.viewmodel.AuthorizationTransactionViewModel

class AuthorizationTransactionFragment : Fragment() {

    private var _binding: FragmentAuthorizationTransactionBinding? = null
    private val binding get() = _binding!!

    private val authorizationViewModel: AuthorizationTransactionViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationTransactionBinding.inflate(inflater, container, false)

        authorizationViewModel.onCreate(binding.root.context)

        binding.btnSubmit.setOnClickListener{
            authorizationViewModel.uploadTransaction(
                111,
                binding.tiCommerceCode.text.toString(),
                binding.tiTerminalCode.text.toString(),
                binding.tiAmount.text.toString(),
                binding.tiCard.text.toString()
            )
        }

        authorizationViewModel.mutableStatusCode.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })

        return binding.root
    }

    private fun showToast(message: String){
        Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
    }
}