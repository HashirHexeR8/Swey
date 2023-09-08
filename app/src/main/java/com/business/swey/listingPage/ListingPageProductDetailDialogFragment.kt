package com.business.swey.listingPage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.business.swey.databinding.FragmentProductPreviewBinding

class ListingPageProductDetailDialogFragment(val imageResId: Int): DialogFragment() {

    lateinit var fragmentViewBinding: FragmentProductPreviewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentViewBinding = FragmentProductPreviewBinding.inflate(inflater, container, false)
        fragmentViewBinding.productImage.setImageResource(imageResId)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return fragmentViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentViewBinding.root.setOnClickListener {
            dismiss()
        }
        fragmentViewBinding.cancelButton.setOnClickListener {
            dismiss()
        }
    }
}