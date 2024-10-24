package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.BuyingScreen
import com.business.swey.features.settings.states.BuyingScreenState

class BuyingFragment : FullScreenDialogComposeFragment() {

    private val buyingScreenState = BuyingScreenState(
        onSettingsClick = ::onSettingsClick,
        onDismiss = ::dismissAllowingStateLoss
    )

    @Composable
    override fun GetContent() {
        BuyingScreen(buyingScreenState = buyingScreenState)
    }

    private fun onSettingsClick(buyingSettingType: BuyingSettingType){
        Toast.makeText(requireContext(), buyingSettingType.name, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val TAG = "BuyingFragment"

        fun getInstance() : BuyingFragment = BuyingFragment()
    }

    enum class BuyingSettingType{
        PURCHASE_HISTORY,
        HOW_TO_BUY
    }
}