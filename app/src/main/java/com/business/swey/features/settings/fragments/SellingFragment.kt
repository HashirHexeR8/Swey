package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.SellingScreen
import com.business.swey.features.settings.states.SellingScreenState

class SellingFragment : FullScreenDialogComposeFragment() {

    private val sellingScreenState = SellingScreenState(
        onSettingsClick = ::onSettingsClick,
        onDismiss = ::dismissAllowingStateLoss
    )

    @Composable
    override fun GetContent() {
        SellingScreen(sellingScreenState = sellingScreenState)
    }

    private fun onSettingsClick(sellingSettingType: SellingSettingType){
        Toast.makeText(requireContext(), sellingSettingType.name, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val TAG = "SellingFragment"

        fun getInstance() : SellingFragment = SellingFragment()
    }

    enum class SellingSettingType{
        SHIPPING,
        PAYMENT,
        SALE
    }
}