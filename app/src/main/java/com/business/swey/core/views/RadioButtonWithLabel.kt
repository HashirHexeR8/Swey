package com.business.swey.core.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.business.swey.R
import com.business.swey.core.fonts.DmSansFamily

@Composable
fun RadioButtonWithLabel(
    selected: Boolean = false,
    label: String,
    selectedColor: Color,
    unSelectedColor: Color,
    selectedColorText: Color,
    unSelectedColorText: Color,
    onOptionSelected: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.clickable(
            interactionSource = remember {
                MutableInteractionSource()
            },
            indication = rememberRipple(
                bounded = true,
                color = colorResource(id = R.color.primary_background_color)
            ),
            onClick = {
                onOptionSelected(selected.not())
            }
        )) {
        RadioButton(
            modifier = Modifier.size(15.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = selectedColor,
                unselectedColor = unSelectedColor
            ),
            selected = selected,
            onClick = {
                onOptionSelected(selected.not())
            }
        )
        Text(
            text = label,
            fontFamily = DmSansFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = if (selected) selectedColorText else unSelectedColorText,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}