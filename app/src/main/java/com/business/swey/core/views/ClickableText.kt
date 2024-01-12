package com.business.swey.core.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.business.swey.R

@Composable
fun ClickableText(
    text: String,
    fontSize: Dp,
    fontFamily: FontFamily,
    color: Int,
    letterSpacing: Dp,
    fontWeight: FontWeight,
    ripplePadding: Dp = 2.dp,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(6.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    color = colorResource(id = R.color.primary_text_color_title)
                ),
                onClick = onClick
            )
    ){
        Text(
            text = text,
            fontSize = fontSize.value.sp,
            fontFamily = fontFamily,
            color = colorResource(id = color),
            letterSpacing = letterSpacing.value.sp,
            fontWeight = fontWeight,
            modifier = Modifier.padding(vertical = ripplePadding, horizontal = ripplePadding / 2),
        )
    }
}
