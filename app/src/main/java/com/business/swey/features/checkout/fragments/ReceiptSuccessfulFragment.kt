package com.business.swey.features.checkout.fragments

import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.databinding.FragmentReceiptSuccessfulBinding

class ReceiptSuccessfulFragment : FullScreenDialogBindingFragment<FragmentReceiptSuccessfulBinding>() {
    override fun getLayout() = R.layout.fragment_receipt_successful


    override fun initViews(binding: FragmentReceiptSuccessfulBinding) {
        dialog?.window?.attributes?.windowAnimations = R.style.FullScreenDialogStyleSlideUpEnterAndSlideDownExit
    }

    override fun setListeners(binding: FragmentReceiptSuccessfulBinding) {
        binding.btnCheckoutBottom.setOnClickListener {
            dismiss()
        }
    }

    companion object{
        const val TAG = "ReceiptSuccessfulFragment"

        fun getInstance(): ReceiptSuccessfulFragment = ReceiptSuccessfulFragment()
    }
}
