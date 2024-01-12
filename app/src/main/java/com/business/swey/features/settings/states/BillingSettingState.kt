package com.business.swey.features.settings.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class BillingSettingState(
    val onDismiss: () -> Unit,
    val onSaveClick: () -> Unit
){
    var isCompany by mutableStateOf(false)
    var nameOrCompany by mutableStateOf("")
    var country by mutableStateOf("")
    var city by mutableStateOf("")
    var address by mutableStateOf("")
    var postalCode by mutableStateOf("")
    var state by mutableStateOf("")
    var preferredCourier by mutableStateOf("")
}
