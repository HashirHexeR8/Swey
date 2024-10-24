package com.business.swey.features.settings.states

import com.business.swey.features.settings.fragments.SettingsFragment

data class SettingsScreenState(
    val isDarkMode: Boolean,
    val onDismiss: () -> Unit,
    val onSettingsClick: (SettingsFragment.SettingType) -> Unit,
    val onDarkModeToggle: (Boolean) -> Unit,
    val onActionClick: (SettingsFragment.ActionType) -> Unit,
    val onButtonClick: () -> Unit,
)