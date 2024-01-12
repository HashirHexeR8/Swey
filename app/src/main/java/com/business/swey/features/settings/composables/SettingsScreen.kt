package com.business.swey.features.settings.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.business.swey.core.views.ClickableText
import com.business.swey.core.views.GradientButton
import com.business.swey.features.settings.states.SettingsScreenState
import com.business.swey.features.settings.fragments.SettingsFragment
import com.business.swey.features.settings.fragments.SettingsFragment.SettingType

@Composable
fun SettingsScreen(
    settingsState: SettingsScreenState
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_background_color))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                Text(
                    text = "More settings",
                    fontSize = 18.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
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
                        ) { settingsState.onDismiss.invoke() })
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .clipToBounds()
                    .background(colorResource(id = R.color.secondary_separator_color))
            )

            Spacer(modifier = Modifier.height(26.dp))

            Column {
                SettingsItem(
                    title = "Profile",
                    iconRes = R.drawable.ic_settings_profile,
                    onClick = {
                        settingsState.onSettingsClick(SettingType.PROFILE)
                    })
                SettingsItem(title = "Privacy and safety",
                    iconRes = R.drawable.ic_settings_privacy,
                    onClick = {
                        settingsState.onSettingsClick(SettingType.PRIVACY_AND_SAFETY)
                    })
                SettingsItem(title = "Content preferences",
                    iconRes = R.drawable.ic_settings_content,
                    onClick = {
                        settingsState.onSettingsClick(SettingType.CONTENT_PREFERENCES)
                    })
                SettingsItem(title = "Settings", iconRes = R.drawable.ic_settings_gear, onClick = {
                    settingsState.onSettingsClick(SettingType.SETTINGS)
                })
                SettingsItem(
                    title = "Discount",
                    iconRes = R.drawable.ic_settings_discount,
                    onClick = {
                        settingsState.onSettingsClick(SettingType.DISCOUNTS)
                    })
                SettingsItem(
                    title = "Support",
                    iconRes = R.drawable.ic_settings_support,
                    onClick = {
                        settingsState.onSettingsClick(SettingType.SUPPORT)
                    })
                SettingsItem(title = "Dark mode",
                    iconRes = R.drawable.ic_settings_mode,
                    isToggleEnabled = settingsState.isDarkMode,
                    onToggleSwitch = { state ->
                        settingsState.onDarkModeToggle(state)
                    },
                    onClick = {
                        settingsState.onSettingsClick(SettingType.DARK_MODE)
                    })
            }

            Spacer(modifier = Modifier.height(18.dp))

            ClickableText(text = "Switch Account",
                fontSize = 13.dp,
                fontFamily = DmSansFamily,
                color = R.color.appPrimaryColor,
                letterSpacing = 0.5.dp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 42.dp),
                onClick = {
                    settingsState.onActionClick(SettingsFragment.ActionType.SWITCH_ACCOUNT)
                })

            Spacer(modifier = Modifier.height(18.dp))

            ClickableText(text = "Add Account",
                fontSize = 13.dp,
                fontFamily = DmSansFamily,
                color = R.color.appPrimaryColor,
                letterSpacing = 0.5.dp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 42.dp),
                onClick = {
                    settingsState.onActionClick(SettingsFragment.ActionType.ADD_ACCOUNT)
                })

            Spacer(modifier = Modifier.height(16.dp))

            ClickableText(text = "Log Out",
                fontSize = 13.dp,
                fontFamily = DmSansFamily,
                color = R.color.app_red,
                letterSpacing = 0.5.dp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 42.dp),
                onClick = {
                    settingsState.onActionClick(SettingsFragment.ActionType.LOGOUT)
                })

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Version 1.0.0",
                    fontSize = 13.sp,
                    fontFamily = DmSansFamily,
                    color = colorResource(id = R.color.primary_text_color),
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(34.dp))
        }

        Column {
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.primary_background_color))
                    .padding(horizontal = 42.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                GradientButton(text = "Upload Products",
                    radius = 100,
                    shadow = 2,
                    onClick = { settingsState.onButtonClick() })
            }
        }
    }
}
