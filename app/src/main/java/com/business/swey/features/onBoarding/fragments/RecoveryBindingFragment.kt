package com.business.swey.features.onBoarding.fragments

import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.databinding.FragmentRecoveryBinding

class RecoveryBindingFragment : FullScreenDialogBindingFragment<FragmentRecoveryBinding>() {
    override fun getLayout() = R.layout.fragment_recovery

    override fun setListeners(binding: FragmentRecoveryBinding) {
        binding.ibBackButton.setOnClickListener {
            dismiss()
        }
        binding.btnSendCode.setOnClickListener {
            openDialogSheet(RecoveryCodeBindingFragment.getInstance(), RecoveryCodeBindingFragment.TAG)
        }
    }

    override fun initViews(binding: FragmentRecoveryBinding) {
        binding.layoutUsername.titleTextView.text = "Username/E-mail"
    }

    companion object {
        const val TAG = "RecoveryFragment"
        fun getInstance(): RecoveryBindingFragment = RecoveryBindingFragment()
    }
}