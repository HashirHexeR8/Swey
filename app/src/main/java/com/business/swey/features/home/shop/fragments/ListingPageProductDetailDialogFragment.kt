package com.business.swey.features.home.shop.fragments

import android.os.Bundle
import com.business.swey.R
import com.business.swey.core.base.BlurredBackgroundDialog
import com.business.swey.databinding.FragmentProductPreviewBinding

class ListingPageProductDetailDialogFragment :
    BlurredBackgroundDialog<FragmentProductPreviewBinding>() {

    override fun getLayout() = R.layout.fragment_product_preview

    override fun initViews(binding: FragmentProductPreviewBinding) {
        super.initViews(binding)
        arguments?.getInt(IMAGE)?.let {
            binding.productImage.setImageResource(it)
        }
    }

    override fun setListeners(binding: FragmentProductPreviewBinding) {
        super.setListeners(binding)
        binding.root.setOnClickListener {
            dismiss()
        }
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val TAG = "ListingPageProductDetailDialogFragment"
        fun getInstance(imageResId: Int): ListingPageProductDetailDialogFragment =
            ListingPageProductDetailDialogFragment().apply {
                val bundle = Bundle()
                bundle.putInt(IMAGE, imageResId)
                arguments = bundle
            }

        private const val IMAGE = "IMAGE"
    }
}