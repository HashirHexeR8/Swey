package com.business.swey.core.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.business.swey.R
import com.business.swey.core.fonts.DmSansFamily

@Composable
fun GradientButton(
    text: String,
    radius: Int,
    shadow: Int,
    onClick: () -> Unit,
    startColor: Color = Color(0xFF5AB0FF),
    endColor: Color = Color(0xFF0079FF)
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            startColor,
            endColor
        )
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(shadow.dp, RoundedCornerShape(radius.dp))
            .background(
                brush = gradientBrush,
                shape = RoundedCornerShape(radius.dp)
            ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        onClick = onClick,
        ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = DmSansFamily,
            color = colorResource(id = R.color.white),
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp),
        )
    }
}