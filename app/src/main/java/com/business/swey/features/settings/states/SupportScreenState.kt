package com.business.swey.features.settings.states

import com.business.swey.features.settings.fragments.SupportFragment.SupportType


data class SupportScreenState(
    val onSettingsClick: (SupportType) -> Unit,
    val onDismiss: () -> Unit
)
