package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.SafetyScreen
import com.business.swey.features.settings.states.SafetyScreenState

class SafetyFragment : FullScreenDialogComposeFragment() {

    private val safetyScreenState = SafetyScreenState(
        onToggleSwitch = ::onToggleSwitch,
        onSettingsClick = ::onSettingsClick,
        onDismiss = ::dismissAllowingStateLoss
    )

    private fun onToggleSwitch(safetySettingType: SafetySettingType, isEnabled: Boolean) {
        Toast.makeText(requireContext(), safetySettingType.name, Toast.LENGTH_SHORT).show()
    }

    @Composable
    override fun GetContent() {
        SafetyScreen(safetyScreenState = safetyScreenState)
    }

    private fun onSettingsClick(safetySettingType: SafetySettingType){
        Toast.makeText(requireContext(), safetySettingType.name, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val TAG = "SafetyFragment"

        fun getInstance() : SafetyFragment = SafetyFragment()
    }

    enum class SafetySettingType{
        REPORT_USERS,
        VERIFIED_STORES,
        AUTHENTIC_ITEMS,
        SHOP_AT_VERIFIED_STORES
    }
}