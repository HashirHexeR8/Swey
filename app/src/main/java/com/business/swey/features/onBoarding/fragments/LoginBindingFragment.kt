package com.business.swey.features.onBoarding.fragments

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.lifecycle.lifecycleScope
import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.core.utils.runDelayed
import com.business.swey.databinding.FragmentLoginBinding
import com.business.swey.features.home.HomeActivity

class LoginBindingFragment : FullScreenDialogBindingFragment<FragmentLoginBinding>() {

    override fun getLayout() = R.layout.fragment_login

    override fun initViews(binding: FragmentLoginBinding) {
        binding.layoutUsername.titleTextView.text = "Username/E-mail"
    }

    override fun setListeners(binding: FragmentLoginBinding) {
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
                openDialogSheet(LoginMobileBindingFragment.getInstance(), LoginMobileBindingFragment.TAG, keepCurrentAlive = false)
                runDelayed(1000, lifecycleScope){
                    binding.checkBoxUseMobile.isChecked = false
                }
            }
        }
        binding.btnNext.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            activity?.finish()
        }
        binding.tvSignUp.setOnClickListener {
            openDialogSheet(SignUpBindingFragment.getInstance(), SignUpBindingFragment.TAG)
        }
    }

    companion object {
        const val TAG = "LoginFragment"
        fun getInstance(): LoginBindingFragment = LoginBindingFragment()
    }
}