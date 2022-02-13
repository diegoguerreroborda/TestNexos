package com.dhgb.testnexos.ui.view.fragments

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dhgb.testnexos.R
import com.dhgb.testnexos.databinding.DialogAlertServerBinding
import com.dhgb.testnexos.databinding.FragmentAuthorizationTransactionBinding
import com.dhgb.testnexos.ui.viewmodel.AuthorizationTransactionViewModel
import kotlinx.android.synthetic.main.dialog_alert_server.*
import kotlinx.android.synthetic.main.dialog_alert_server.view.*


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
//            showToast(it)
            showDialog(binding.root.context, it)
        })

        return binding.root
    }

    private fun showDialog(context: Context, code: Int) {
        val bindingD = DialogAlertServerBinding.inflate(LayoutInflater.from(context))
        val dialog = Dialog(binding.root.context)
        val width = resources.displayMetrics.widthPixels * 0.85
        val height = resources.displayMetrics.heightPixels * 0.4

        bindingD.lottieAnim.setAnimation(changeLottie(code))
        bindingD.lottieAnim.playAnimation()
        bindingD.tvResTitle.text = changeDataDialog(code)
        bindingD.tvResBody.text = code.toString()
        bindingD.btnOk.setOnClickListener{
            dialog.dismiss()
        }

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bindingD.root)
        dialog.show()
        dialog.setCancelable(true)
        dialog.window!!.setLayout(width.toInt(), height.toInt())
    }

    private fun changeDataDialog(code: Int): String {
        return when(code) {
            202 -> "Nice: Saved data"
            400 -> "Wrong: Bad Request"
            401 -> "Wrong: Unauthorized"
            -1 -> "Wrong: Server Error"
            else -> "Unexpected error"
        }
    }

    private fun changeLottie(code: Int): Int {
        return if(code == 202){
            R.raw.lottie_yes
        }else {
            R.raw.lottie_no
        }
    }
}