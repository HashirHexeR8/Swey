package com.business.swey.core.base

import com.business.swey.R
import com.business.swey.databinding.TestLayoutBinding

class TestLayoutFragment : FullScreenDialogBindingFragment<TestLayoutBinding>() {
    override fun getLayout() = R.layout.test_layout


    override fun initViews(binding: TestLayoutBinding) {
        binding.collapsingToolbar.title = "Swey Test"
    }

    override fun setListeners(binding: TestLayoutBinding) {}

    companion object{
        const val TAG = "TestLayoutFragment"

        fun getInstance(): TestLayoutFragment = TestLayoutFragment()
    }
}