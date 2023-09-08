package com.business.swey.listingDetails

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.business.swey.databinding.FragmentTopSizeFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TopSizeFilterFragment: BottomSheetDialogFragment() {

    companion object Factory {
        fun create(): TopSizeFilterFragment = TopSizeFilterFragment()
    }

    lateinit var filterSheetViewBinding: FragmentTopSizeFilterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        filterSheetViewBinding = FragmentTopSizeFilterBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return filterSheetViewBinding.root
    }
}