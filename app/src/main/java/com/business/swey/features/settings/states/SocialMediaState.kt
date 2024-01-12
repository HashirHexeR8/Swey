package com.business.swey.features.settings.states

data class SocialMediaState(
    val onInstagramShare: () -> Unit,
    val onTwitterShare: () -> Unit,
    val onFacebookShare: () -> Unit,
    val onTikTokShare: () -> Unit,
    val onDismiss: () -> Unit
)
