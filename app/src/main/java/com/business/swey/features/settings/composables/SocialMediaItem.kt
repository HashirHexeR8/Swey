package com.business.swey.features.settings.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.business.swey.R
import com.business.swey.core.extensions.capitalize
import com.business.swey.core.fonts.DmSansFamily
import com.business.swey.features.settings.fragments.SocialMediaFragment.SocialMediaTypes

@Composable
fun SocialMediaItem(type: SocialMediaTypes, iconRes: Int, onClick: (SocialMediaTypes) -> Unit) {
    
    var isLinked by remember {
        mutableStateOf(false)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = colorResource(id = R.color.primary_text_color_title)),
                    onClick = {
                        onClick.invoke(type)
                        isLinked = isLinked.not()
                    })
                .then(Modifier.padding(vertical = 24.dp, horizontal = 40.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )

            Text(
                text = type.name.capitalize(),
                fontSize = 13.sp,
                fontFamily = DmSansFamily,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = colorResource(id = if (!isLinked) R.color.primary_text_color_title else R.color.appPrimaryColor),
                modifier = Modifier.padding(start = 21.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            if (isLinked){
                Text(
                    text = "My.Name",
                    fontSize = 13.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = colorResource(id = R.color.appPrimaryColor),
                    modifier = Modifier
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_linked),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            else{
                Image(
                    painter = painterResource(id = R.drawable.ic_link),
                    contentDescription = null,
                    modifier = Modifier
                )

                Text(
                    text = "Link",
                    fontSize = 13.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(1.dp)
                .background(colorResource(id = R.color.secondary_separator_color))
        )
    }
}
