package com.business.swey.features.settings.states

import com.business.swey.features.settings.fragments.MoreSettingsFragment.MoreSettingsType

data class MoreSettingsState(
    val onSettingsClick: (MoreSettingsType) -> Unit,
    val onDismiss: () -> Unit
)