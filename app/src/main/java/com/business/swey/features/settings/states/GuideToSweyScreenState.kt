package com.business.swey.features.settings.states

import com.business.swey.features.settings.fragments.GuideToSweyFragment.GuideToSweyType

data class GuideToSweyScreenState (
    val onSettingsClick: (GuideToSweyType) -> Unit,
    val onDismiss: () -> Unit
)