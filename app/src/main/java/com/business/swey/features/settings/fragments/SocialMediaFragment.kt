package com.business.swey.features.settings.fragments

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.business.swey.core.base.FullScreenDialogComposeFragment
import com.business.swey.features.settings.composables.SocialMediaScreen
import com.business.swey.features.settings.states.SocialMediaState

class SocialMediaFragment : FullScreenDialogComposeFragment() {

    private val socialMediaState = SocialMediaState(
        onInstagramShare = ::onInstagramShare,
        onTwitterShare = ::onTwitterShare,
        onFacebookShare = ::onFacebookShare,
        onTikTokShare = ::onTikTokShare,
        onDismiss = ::dismissAllowingStateLoss
    )

    @Composable
    override fun GetContent() {
        SocialMediaScreen(socialMediaState = socialMediaState)
    }

    private fun onTikTokShare() {
        Toast.makeText(requireContext(), "Tiktok", Toast.LENGTH_SHORT).show()
    }

    private fun onFacebookShare() {
        Toast.makeText(requireContext(), "Facebook", Toast.LENGTH_SHORT).show()
    }

    private fun onTwitterShare() {
        Toast.makeText(requireContext(), "Twitter", Toast.LENGTH_SHORT).show()
    }

    private fun onInstagramShare() {
        Toast.makeText(requireContext(), "Instagram", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "SocialMediaFragment"

        fun getInstance(): SocialMediaFragment = SocialMediaFragment()
    }

    enum class SocialMediaTypes{
        INSTAGRAM,
        TWITTER,
        FACEBOOK,
        TIKTOK
    }
}