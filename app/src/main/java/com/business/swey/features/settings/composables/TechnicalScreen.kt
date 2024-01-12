package com.business.swey.features.settings.composables

import com.business.swey.features.settings.fragments.TechnicalIssuesFragment.TechnicalSettingsType

data class TechnicalScreenState(
    val onSettingsClick: (TechnicalSettingsType) -> Unit,
    val onDismiss: () -> Unit
)
