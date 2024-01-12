package com.business.swey.features.settings.states

import com.business.swey.features.settings.fragments.SafetyFragment.SafetySettingType

data class SafetyScreenState(
    val onToggleSwitch: (SafetySettingType, Boolean) -> Unit,
    val onSettingsClick: (SafetySettingType) -> Unit,
    val onDismiss: () -> Unit
)