package com.business.swey.features.onBoarding.fragments

import androidx.lifecycle.lifecycleScope
import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.core.utils.runDelayed
import com.business.swey.databinding.FragmentLoginMobileBinding

class LoginMobileBindingFragment : FullScreenDialogBindingFragment<FragmentLoginMobileBinding>() {

    override fun getLayout() = R.layout.fragment_login_mobile

    override fun initViews(binding: FragmentLoginMobileBinding) {}

    override fun setListeners(binding: FragmentLoginMobileBinding) {
        binding.ibBackButton.setOnClickListener {
            dismiss()
        }
        binding.checkBoxUseMobile.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                openDialogSheet(LoginBindingFragment.getInstance(), LoginBindingFragment.TAG, keepCurrentAlive = false)
                runDelayed(1000, lifecycleScope){
                    binding.checkBoxUseMobile.isChecked = false
                }
            }
        }
        binding.tvSignUp.setOnClickListener {
            openDialogSheet(SignUpBindingFragment.getInstance(), SignUpBindingFragment.TAG)
        }
        binding.btnNext.setOnClickListener {
            openDialogSheet(RecoveryBindingFragment.getInstance(), RecoveryBindingFragment.TAG)
        }
    }

    companion object {
        const val TAG = "LoginMobileFragment"
        fun getInstance(): LoginMobileBindingFragment = LoginMobileBindingFragment()
    }
}