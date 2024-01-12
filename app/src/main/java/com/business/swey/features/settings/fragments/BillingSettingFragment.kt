package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.BillingSettingScreen
import com.business.swey.features.settings.states.BillingSettingState

class BillingSettingFragment : FullScreenDialogComposeFragment() {

    private val billingSettingState = BillingSettingState(
        onSaveClick = ::onSaveClick,
        onDismiss = ::dismissAllowingStateLoss
    )
    @Composable
    override fun GetContent() {
        billingSettingState.isCompany = true
        billingSettingState.nameOrCompany = "Nike Company - Biggest Store"
        billingSettingState.country = "United states"
        billingSettingState.city = "New York"
        billingSettingState.address = "New York, United states"
        billingSettingState.postalCode = "10000"
        billingSettingState.state = "Manhattan"
        billingSettingState.preferredCourier = "Fedex"
        BillingSettingScreen(
            billingSettingState = billingSettingState
        )
    }

    private fun onSaveClick() {
        Toast.makeText(requireContext(), "Saved changes", Toast.LENGTH_SHORT).show()
    }


    companion object{
        const val TAG = "BillingSettingFragment"

        fun getInstance(): BillingSettingFragment = BillingSettingFragment()
    }
}