package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.ProfileSettingsScreen
import com.business.swey.features.settings.states.ProfileSettingState

class ProfileSettingsFragment : FullScreenDialogComposeFragment() {

    private val profileSettingState: ProfileSettingState = ProfileSettingState(
        onSaveClick = ::onSaveClick,
        onImageClick = ::onImageClick,
        onOtherSocialMediaClick = ::onOtherSocialMediaClick,
        onDismiss = ::dismissAllowingStateLoss
    )

    private fun onOtherSocialMediaClick() {
        openDialogSheet(SocialMediaFragment.getInstance(), SocialMediaFragment.TAG)
    }

    @Composable
    override fun GetContent() {
        profileSettingState.name = "Nike Company - Biggest Store"
        profileSettingState.username = "NikeCompany"
        profileSettingState.biography = "NIKE Inc. is an American multinational corporation that is Engaged in the design, development, manufacturing, and Worldwide marketing"
        profileSettingState.website = "NikeCompany.com"
        profileSettingState.phone = "+1940690245"
        profileSettingState.location = "Yazd, Iran"
        ProfileSettingsScreen(
            profileSettingState = profileSettingState
        )
    }

    private fun onImageClick() {
        Toast.makeText(requireContext(), "Upload Image", Toast.LENGTH_SHORT).show()
    }

    private fun onSaveClick() {
        Toast.makeText(requireContext(), "Saved Changes", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "ProfileSettingsFragment"

        fun getInstance(): ProfileSettingsFragment = ProfileSettingsFragment()
    }
}