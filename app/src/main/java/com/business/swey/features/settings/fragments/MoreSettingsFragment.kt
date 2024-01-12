package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.MoreSettingsScreen
import com.business.swey.features.settings.states.MoreSettingsState

class MoreSettingsFragment : FullScreenDialogComposeFragment() {

    private val moreSettingsState = MoreSettingsState(
        onSettingsClick = ::onSettingsClick,
        onDismiss = ::dismissAllowingStateLoss
    )

    private fun onSettingsClick(type: MoreSettingsType) {
        when(type){
            MoreSettingsType.BILLING -> {
                openDialogSheet(BillingSettingFragment.getInstance(), BillingSettingFragment.TAG)
            }
            else -> {
                Toast.makeText(requireContext(), type.name, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    override fun GetContent() {
        MoreSettingsScreen(
            moreSettingsState = moreSettingsState
        )
    }

    companion object{
        const val TAG = "MoreSettingsFragment"

        fun getInstance(): MoreSettingsFragment = MoreSettingsFragment()
    }

    enum class MoreSettingsType {
        CARDS,
        DEPOSIT_MONEY,
        CONVERT_SWEY_COINS,
        PAYPAL,
        CURRENCY,
        BILLING,
        NOTIFICATION
    }
}