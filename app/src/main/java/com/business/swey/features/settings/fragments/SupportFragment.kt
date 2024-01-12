package com.business.swey.features.settings.fragments

import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.SupportScreen
import com.business.swey.features.settings.states.SupportScreenState

class SupportFragment : FullScreenDialogComposeFragment() {

    private val supportScreenState = SupportScreenState(
        onSettingsClick = ::onSettingsClick,
        onDismiss = ::dismissAllowingStateLoss
    )

    private fun onSettingsClick(supportType: SupportType) {
        when(supportType){
            SupportType.SELLING -> openDialogSheet(SellingFragment.getInstance(), SellingFragment.TAG)
            SupportType.BUYING -> openDialogSheet(BuyingFragment.getInstance(), BuyingFragment.TAG)
            SupportType.SAFETY -> openDialogSheet(SafetyFragment.getInstance(), SafetyFragment.TAG)
            SupportType.GUIDE -> openDialogSheet(GuideToSweyFragment.getInstance(), GuideToSweyFragment.TAG)
            SupportType.ISSUES -> openDialogSheet(TechnicalIssuesFragment.getInstance(), TechnicalIssuesFragment.TAG)
        }
    }

    @Composable
    override fun GetContent() {
        SupportScreen(supportScreenState = supportScreenState)
    }


    companion object {
        const val TAG = "SupportFragment"

        fun getInstance(): SupportFragment = SupportFragment()
    }

    enum class SupportType {
        SELLING,
        BUYING,
        SAFETY,
        GUIDE,
        ISSUES
    }
}