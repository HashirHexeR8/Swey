package com.business.swey.listingDetails

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.business.swey.databinding.FragmentMainFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterSheetFragment(): BottomSheetDialogFragment() {

    companion object Factory {
        fun create(): FilterSheetFragment = FilterSheetFragment()
    }

    lateinit var filterSheetViewBinding: FragmentMainFilterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        filterSheetViewBinding = FragmentMainFilterBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return filterSheetViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterSheetViewBinding.rlSizeFilterContainer.setOnClickListener {
            SizeTileFilterSheetFragment.create().show(parentFragmentManager, "FilterSheetFragment")
        }
    }


}