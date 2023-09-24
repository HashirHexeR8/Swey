package com.business.swey.features.onBoarding.fragments

import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.databinding.FragmentLookingGoodBinding

class LookingGoodBindingFragment : FullScreenDialogBindingFragment<FragmentLookingGoodBinding>() {
    override fun getLayout() = R.layout.fragment_looking_good

    override fun setListeners(binding: FragmentLookingGoodBinding) {
        binding.ibBackButton.setOnClickListener {
            dismiss()
        }

        binding.btnComplete.setOnClickListener {
            openDialogSheet(LoginBindingFragment.getInstance(), LoginBindingFragment.TAG)
        }
    }

    override fun initViews(binding: FragmentLookingGoodBinding) {
    }

    companion object {
        const val TAG = "LookingGoodFragment"
        fun getInstance(): LookingGoodBindingFragment = LookingGoodBindingFragment()
    }
}