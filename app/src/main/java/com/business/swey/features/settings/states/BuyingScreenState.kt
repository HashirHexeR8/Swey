package com.business.swey.features.settings.states

import com.business.swey.features.settings.fragments.BuyingFragment.BuyingSettingType

data class BuyingScreenState(
    val onSettingsClick: (BuyingSettingType) -> Unit,
    val onDismiss: () -> Unit
)
