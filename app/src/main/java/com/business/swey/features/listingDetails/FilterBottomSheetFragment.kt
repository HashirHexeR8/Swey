package com.business.swey.features.listingDetails

import com.business.swey.R
import com.business.swey.core.base.RoundedBottomSheetDialogBindingFragment
import com.business.swey.databinding.BottomSheetMainFilterBinding

class FilterBottomSheetFragment: RoundedBottomSheetDialogBindingFragment<BottomSheetMainFilterBinding>() {
    override fun getLayout() = R.layout.bottom_sheet_main_filter

    override fun initViews(binding: BottomSheetMainFilterBinding) {}

    override fun setListeners(binding: BottomSheetMainFilterBinding) {
        binding.rlSizeFilterContainer.setOnClickListener {
            openBottomSheet(SizeTileFilterSheetFragment.getInstance(), "FilterSheetFragment")
        }
    }

    companion object {
        fun getInstance(): FilterBottomSheetFragment = FilterBottomSheetFragment()
    }
}