package com.business.swey.core.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientSwitch(
    startTrackColor: Color = Color(0xFF5AB0FF),
    endTrackColor: Color = Color(0xFF0079FF),
    uncheckedTrackColor: Color = Color(0xFFD0D0D0),
    thumbRadius: Dp = 12.dp,
    thumbColor: Color = Color.White,
    thumbGap: Dp = 3.dp,
    onCheckedChange: (Boolean) -> Unit,
    defaultValue: Boolean,
) {

    val switchON = remember {
        mutableStateOf(defaultValue)
    }

    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            if (switchON.value) startTrackColor else uncheckedTrackColor,
            if (switchON.value) endTrackColor else uncheckedTrackColor
        )
    )

    // To move the thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON.value)
            with(LocalDensity.current) { (thumbRadius.times(3.5f) + thumbGap).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + thumbGap).toPx() },
        label = "toggleAnimation"
    )

    Canvas(
        modifier = Modifier
            .size(width = thumbRadius.times(4.5f) + thumbGap.times(2), height = thumbRadius * 2 + thumbGap * 2)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        switchON.value = !switchON.value
                        onCheckedChange.invoke(switchON.value)
                    }
                )
            }
    ) {

        drawRoundRect(
            brush = gradientBrush,
            cornerRadius = CornerRadius(x = 100.dp.toPx(), y = 100.dp.toPx())
        )

        // Thumb
        drawCircle(
            color = thumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }
}
