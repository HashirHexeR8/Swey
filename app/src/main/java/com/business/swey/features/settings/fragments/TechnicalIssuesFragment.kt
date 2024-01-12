package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.TechnicalIssuesScreen
import com.business.swey.features.settings.composables.TechnicalScreenState

class TechnicalIssuesFragment: FullScreenDialogComposeFragment() {

    private val technicalScreenState = TechnicalScreenState(
        onSettingsClick = ::onSettingsClick,
        onDismiss = ::dismissAllowingStateLoss
    )

    @Composable
    override fun GetContent() {
        TechnicalIssuesScreen(technicalScreenState = technicalScreenState)
    }

    private fun onSettingsClick(technicalSettingType: TechnicalSettingsType) {
        Toast.makeText(requireContext(), technicalSettingType.name, Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val TAG = "TechnicalIssuesFragment"
        fun getInstance(): TechnicalIssuesFragment = TechnicalIssuesFragment()
    }

    enum class TechnicalSettingsType {
        TROUBLESHOOT,
        RESTART,
        UNINSTALL_AND_REINSTALL
    }
}