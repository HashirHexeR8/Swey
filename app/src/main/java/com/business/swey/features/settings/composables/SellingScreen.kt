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
import com.business.swey.features.settings.fragments.SellingFragment.SellingSettingType
import com.business.swey.features.settings.states.SellingScreenState

@Composable
fun SellingScreen(sellingScreenState: SellingScreenState) {
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
                    .clickable { sellingScreenState.onDismiss() })


            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Selling",
                fontSize = 18.sp,
                fontFamily = DmSansFamily,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.primary_text_color_title)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.ic_cross),
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
                    ) { sellingScreenState.onDismiss() }
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .clipToBounds()
                .background(colorResource(id = R.color.secondary_separator_color))
        )

        Spacer(modifier = Modifier.height(26.dp))

        SettingsItem(title = "Shipping", iconRes = R.drawable.ic_shipping,
            onClick = {
                sellingScreenState.onSettingsClick(SellingSettingType.SHIPPING)
            })
        SettingsItem(title = "Payment", iconRes = R.drawable.ic_payment_card,
            onClick = {
                sellingScreenState.onSettingsClick(SellingSettingType.PAYMENT)
            })
        SettingsItem(title = "Sale", iconRes = R.drawable.ic_buying,
            onClick = {
                sellingScreenState.onSettingsClick(SellingSettingType.SALE)
            })
    }
}