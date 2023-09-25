package com.business.swey.features.onBoarding.fragments

import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.databinding.FragmentGetStartedBinding

class GetStartedFragment : FullScreenDialogBindingFragment<FragmentGetStartedBinding>() {


    override fun getLayout() = R.layout.fragment_get_started

    override fun initViews(binding: FragmentGetStartedBinding) {}

    override fun setListeners(binding: FragmentGetStartedBinding) {
        binding.btnContinue.setOnClickListener {
            openDialogSheet(SignUpBindingFragment.getInstance(), SignUpBindingFragment.TAG)
        }
        binding.tvSignIn.setOnClickListener {
            openDialogSheet(LoginBindingFragment.getInstance(), LoginBindingFragment.TAG)
        }
    }

    companion object {
        const val TAG = "GetStartedFragment"
        fun getInstance(): GetStartedFragment = GetStartedFragment()
    }
}