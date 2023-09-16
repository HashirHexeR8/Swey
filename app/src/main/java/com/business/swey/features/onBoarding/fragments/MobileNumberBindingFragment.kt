package com.business.swey.features.onBoarding.fragments

import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.databinding.FragmentMobileSignUpBinding

class MobileNumberBindingFragment : FullScreenDialogBindingFragment<FragmentMobileSignUpBinding>() {
    override fun getLayout() = R.layout.fragment_mobile_sign_up

    override fun setListeners(binding: FragmentMobileSignUpBinding) {
        binding.ibBackButton.setOnClickListener {
            dismiss()
        }
        binding.btnContinue.setOnClickListener {
            openDialogSheet(CodeConfirmationBindingFragment.getInstance(), CodeConfirmationBindingFragment.TAG)
        }
    }

    override fun initViews(binding: FragmentMobileSignUpBinding) {

    }

    companion object {
        const val TAG = "MobileNumberFragment"
        fun getInstance(): MobileNumberBindingFragment = MobileNumberBindingFragment()
    }
}