package com.business.swey.features.settings.states

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.business.swey.R

data class ProfileSettingState(
    @DrawableRes
    val image: Int = R.drawable.userprofile,
    val onSaveClick: () -> Unit,
    val onImageClick: () -> Unit,
    val onOtherSocialMediaClick: () -> Unit,
    val onDismiss: () -> Unit
) {
    var name by mutableStateOf("")
    var username by mutableStateOf("")
    var biography by mutableStateOf("")
    var website by mutableStateOf("")
    var phone by mutableStateOf("")
    var location by mutableStateOf("")
}
