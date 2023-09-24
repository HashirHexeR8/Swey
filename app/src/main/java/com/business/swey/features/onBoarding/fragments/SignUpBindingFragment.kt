package com.business.swey.features.onBoarding.fragments

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.lifecycle.lifecycleScope
import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.core.utils.runDelayed
import com.business.swey.databinding.FragmentSignUpBinding

class SignUpBindingFragment : FullScreenDialogBindingFragment<FragmentSignUpBinding>() {

    override fun getLayout() = R.layout.fragment_sign_up

    override fun setListeners(binding: FragmentSignUpBinding) {
        binding.ibBackButton.setOnClickListener {
            dismiss()
        }
        binding.layoutPassword.btPasswordToggle.setOnClickListener {
            if (binding.layoutPassword.inputEditText.transformationMethod == PasswordTransformationMethod.getInstance()) {
                binding.layoutPassword.inputEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.layoutPassword.btPasswordToggle.setImageResource(R.drawable.ic_eye)
            } else {
                binding.layoutPassword.inputEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.layoutPassword.btPasswordToggle.setImageResource(R.drawable.ic_eye_selected)
            }
        }
        binding.checkBoxUseMobile.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                openDialogSheet(MobileNumberBindingFragment.getInstance(), MobileNumberBindingFragment.TAG)
                runDelayed(1000, lifecycleScope){
                    binding.checkBoxUseMobile.isChecked = false
                }
            }
        }
        binding.btnNext.setOnClickListener {
            openDialogSheet(UploadPictureBindingFragment.getInstance(), UploadPictureBindingFragment.TAG)
        }
        binding.tvSignIn.setOnClickListener {
            openDialogSheet(LoginBindingFragment.getInstance(), LoginBindingFragment.TAG)
        }
    }

    override fun initViews(binding: FragmentSignUpBinding) {}

    companion object {
        const val TAG = "SignInFragment"
        fun getInstance(): SignUpBindingFragment = SignUpBindingFragment()
    }
}