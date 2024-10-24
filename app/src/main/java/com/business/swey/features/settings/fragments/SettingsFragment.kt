package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.core.utils.SharedPreferences
import com.business.swey.core.utils.SharedPreferences.IS_DARK_MODE_ENABLED
import com.business.swey.core.utils.runDelayed
import com.business.swey.core.utils.ThemeUtil
import com.business.swey.features.settings.composables.SettingsScreen
import com.business.swey.features.settings.states.SettingsScreenState

class SettingsFragment : FullScreenDialogComposeFragment() {

    @Composable
    override fun GetContent() {
        SettingsScreen(
            SettingsScreenState(
                isDarkMode = SharedPreferences.getPreference(
                    requireContext(),
                    IS_DARK_MODE_ENABLED,
                    defaultValue = false
                ) as Boolean,
                onDismiss = ::dismissAllowingStateLoss,
                onActionClick = ::onActionClick,
                onSettingsClick = ::onSettingsClick,
                onButtonClick = ::onButtonClick,
                onDarkModeToggle = ::onDarkModeToggle
            )
        )
    }

    private fun onDarkModeToggle(isDark: Boolean) {
        SharedPreferences.storePreference(requireContext(), IS_DARK_MODE_ENABLED, value = isDark)
        runDelayed(150, lifecycleScope) {
            ThemeUtil.toggleTheme(requireActivity())
        }
    }

    private fun onButtonClick() {
        Toast.makeText(requireContext(), "Upload", Toast.LENGTH_SHORT).show()
    }

    private fun onSettingsClick(settingType: SettingType) {
        when (settingType) {
            SettingType.PROFILE -> openDialogSheet(
                ProfileSettingsFragment.getInstance(),
                ProfileSettingsFragment.TAG
            )
            SettingType.SETTINGS -> openDialogSheet(
                MoreSettingsFragment.getInstance(),
                MoreSettingsFragment.TAG
            )
            SettingType.SUPPORT -> openDialogSheet(
                SupportFragment.getInstance(),
                SupportFragment.TAG
            )

            else -> Toast.makeText(requireContext(), settingType.name, Toast.LENGTH_SHORT).show()

        }
    }

    private fun onActionClick(actionType: ActionType) {
        Toast.makeText(requireContext(), actionType.name, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "SettingsFragment"

        fun getInstance(): SettingsFragment = SettingsFragment()
    }

    enum class SettingType {
        PROFILE,
        PRIVACY_AND_SAFETY,
        CONTENT_PREFERENCES,
        SETTINGS,
        DISCOUNTS,
        SUPPORT,
        DARK_MODE
    }

    enum class ActionType {
        SWITCH_ACCOUNT,
        ADD_ACCOUNT,
        LOGOUT,
    }
}
