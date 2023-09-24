package com.business.swey.features.onBoarding.fragments

import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.databinding.FragmentUploadPictureBinding

class UploadPictureBindingFragment : FullScreenDialogBindingFragment<FragmentUploadPictureBinding>() {
    override fun getLayout() = R.layout.fragment_upload_picture

    override fun setListeners(binding: FragmentUploadPictureBinding) {
        binding.ibBackButton.setOnClickListener {
            dismiss()
        }
        binding.btnChooseImage.setOnClickListener {
            openDialogSheet(LookingGoodBindingFragment.getInstance(), LookingGoodBindingFragment.TAG)
        }
        binding.tvSkip.setOnClickListener {
            openDialogSheet(LoginBindingFragment.getInstance(), LoginBindingFragment.TAG)
        }
    }

    override fun initViews(binding: FragmentUploadPictureBinding) {}

    companion object {
        const val TAG = "UploadPictureFragment"
        fun getInstance(): UploadPictureBindingFragment = UploadPictureBindingFragment()
    }
}