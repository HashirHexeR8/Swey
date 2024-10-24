package com.business.swey.features.settings.states

import com.business.swey.features.settings.fragments.SellingFragment.SellingSettingType

data class SellingScreenState (
    val onSettingsClick: (SellingSettingType) -> Unit,
    val onDismiss: () -> Unit
)
