package com.business.swey.features.settings.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.business.swey.R
import com.business.swey.core.fonts.DmSansFamily
import com.business.swey.features.settings.fragments.MoreSettingsFragment.MoreSettingsType
import com.business.swey.features.settings.states.MoreSettingsState

@Composable
fun MoreSettingsScreen(moreSettingsState: MoreSettingsState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_background_color))
            .padding(bottom = 100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 42.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .clickable { moreSettingsState.onDismiss() })


            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Sharing to Other Apps",
                fontSize = 18.sp,
                fontFamily = DmSansFamily,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.primary_text_color_title)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(painter = painterResource(id = R.drawable.ic_cross),
                contentDescription = null,
                modifier = Modifier
                    .size(12.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = false, color = colorResource(
                                id = R.color.primary_text_color_title
                            )
                        )
                    ) { moreSettingsState.onDismiss.invoke() })
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .clipToBounds()
                .background(colorResource(id = R.color.secondary_separator_color))
        )

        Spacer(modifier = Modifier.height(26.dp))

        SettingsItem(title = "Cards", iconRes = R.drawable.ic_card_master,
            onClick = {
            moreSettingsState.onSettingsClick(MoreSettingsType.CARDS)
        })
        SettingsItem(title = "Desposit money in Swey account", iconRes = R.drawable.ic_deposit,
            onClick = {
            moreSettingsState.onSettingsClick(MoreSettingsType.DEPOSIT_MONEY)
        })
        SettingsItem(title = "Convert Swey coin", iconRes = R.drawable.ic_convert,
            onClick = {
            moreSettingsState.onSettingsClick(MoreSettingsType.CONVERT_SWEY_COINS)
        })
        SettingsItem(title = "PayPal", iconRes = R.drawable.ic_paypal,
            onClick = {
            moreSettingsState.onSettingsClick(MoreSettingsType.PAYPAL)
        })
        SettingsItem(title = "Currency", iconRes = R.drawable.ic_currency,
            onClick = {
            moreSettingsState.onSettingsClick(MoreSettingsType.CURRENCY)
        })
        SettingsItem(title = "Billing", iconRes = R.drawable.ic_billing,
            onClick = {
            moreSettingsState.onSettingsClick(MoreSettingsType.BILLING)
        })
        SettingsItem(title = "Notification", iconRes = R.drawable.ic_settings_content,
            onClick = {
            moreSettingsState.onSettingsClick(MoreSettingsType.NOTIFICATION)
        })
    }
}