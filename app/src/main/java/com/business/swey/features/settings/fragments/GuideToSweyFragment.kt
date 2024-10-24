package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.GuideToSweyScreen
import com.business.swey.features.settings.states.GuideToSweyScreenState

class GuideToSweyFragment: FullScreenDialogComposeFragment() {

    private val guideToSweyScreenState = GuideToSweyScreenState(
        onSettingsClick = ::onSettingsClick,
        onDismiss = ::dismissAllowingStateLoss
    )

    @Composable
    override fun GetContent() {
        GuideToSweyScreen(guideToSweyScreenState = guideToSweyScreenState)
    }

    private fun onSettingsClick(guideToSweySettingType: GuideToSweyType){
        Toast.makeText(requireContext(), guideToSweySettingType.name, Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val TAG = "GuideToSweyFragment"
        fun getInstance(): GuideToSweyFragment = GuideToSweyFragment()
    }

    enum class GuideToSweyType{
        RULES,
        TERMS_OF_SERVICES
    }
}