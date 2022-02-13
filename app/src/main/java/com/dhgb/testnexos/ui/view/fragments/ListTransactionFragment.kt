package com.dhgb.testnexos.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity
import com.dhgb.testnexos.databinding.FragmentListTransactionBinding
import com.dhgb.testnexos.ui.view.adapter.AuthAdapter
import com.dhgb.testnexos.ui.viewmodel.ListTransactionViewModel

class ListTransactionFragment : Fragment() {

    private var _binding: FragmentListTransactionBinding? = null
    private val binding get() = _binding!!

    private val listTransactionViewModel: ListTransactionViewModel by viewModels()

    private lateinit var adapter: AuthAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTransactionBinding.inflate(inflater, container, false)

        listTransactionViewModel.onCreate(binding.root.context)

        listTransactionViewModel.mutableListAuth.observe(viewLifecycleOwner, {
            initRecyclerView(it)
        })

        binding.tiReceiptId.addTextChangedListener{
            listTransactionViewModel.getAuthByReceiptId(it.toString())
        }

        return binding.root
    }

    private fun initRecyclerView(authList: List<AuthenticationEntity>){
        adapter = AuthAdapter(authList, object: AuthAdapter.OnItemClickListener{
            override fun onItemClick(authenticationEntity: AuthenticationEntity) {
                val direction = ListTransactionFragmentDirections
                    .actionListTransactionFragmentToTransactionDetailFragment(authenticationEntity)
                Navigation.findNavController(binding.root).navigate(direction)
            }
        })
        binding.rvAuthList.layoutManager = LinearLayoutManager(context)
        binding.rvAuthList.adapter = adapter
    }
}