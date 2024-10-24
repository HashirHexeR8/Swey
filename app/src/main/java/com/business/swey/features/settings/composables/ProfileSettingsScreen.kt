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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.business.swey.R
import com.business.swey.core.extensions.isPhoneNumber
import com.business.swey.core.fonts.DmSansFamily
import com.business.swey.core.fonts.PoppinsFamily
import com.business.swey.core.views.ClickableText
import com.business.swey.core.views.GradientButton
import com.business.swey.features.settings.states.ProfileSettingState

@Composable
fun ProfileSettingsScreen(profileSettingState: ProfileSettingState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_background_color))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 42.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { profileSettingState.onDismiss() })

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Profile",
                    fontSize = 18.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                )

            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .clipToBounds()
                    .background(colorResource(id = R.color.secondary_separator_color))
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(42.dp))

                Image(
                    painter = painterResource(id = profileSettingState.image),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(84.dp)
                        .clip(CircleShape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true, color = colorResource(id = R.color.primary_text_color_title)),
                            onClick = profileSettingState.onImageClick
                        ),
                    contentDescription = "Image"
                )

                Spacer(modifier = Modifier.width(24.dp))

                Text(
                    text = "Change Profile Photo",
                    fontSize = 13.sp,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.primary_text_color),
                )
            }

            Column(modifier = Modifier.padding(horizontal = 42.dp)) {
                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = "Name:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = profileSettingState.name,
                    placeholder = {
                        Text(
                            text = "Name",
                            color = colorResource(id = R.color.primary_text_color),
                            fontWeight = FontWeight.Medium,
                            fontFamily = PoppinsFamily,
                            fontSize = 11.sp,
                            modifier = Modifier.alpha(0.4f)
                        )
                    },
                    singleLine = true,
                    onValueChange = {
                        profileSettingState.name = it
                    },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.primary_text_color),
                        fontWeight = FontWeight.Medium,
                        fontFamily = PoppinsFamily,
                        fontSize = 11.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    shape = RoundedCornerShape(7.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.edit_text_background),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Username:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = profileSettingState.username,
                    placeholder = {
                        Text(
                            text = "Username",
                            color = colorResource(id = R.color.primary_text_color),
                            fontWeight = FontWeight.Medium,
                            fontFamily = PoppinsFamily,
                            fontSize = 11.sp,
                            modifier = Modifier.alpha(0.4f)
                        )
                    },
                    singleLine = true,
                    onValueChange = {
                        profileSettingState.username = it
                    },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.primary_text_color),
                        fontWeight = FontWeight.Medium,
                        fontFamily = PoppinsFamily,
                        fontSize = 11.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    shape = RoundedCornerShape(7.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.edit_text_background),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Biography:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                    TextField(
                        value = profileSettingState.biography,
                        placeholder = {
                            Text(
                                text = "About you",
                                color = colorResource(id = R.color.primary_text_color),
                                fontWeight = FontWeight.Medium,
                                fontFamily = PoppinsFamily,
                                fontSize = 11.sp,
                                modifier = Modifier.alpha(0.4f)
                            )
                        },
                        onValueChange = {
                            if (it.length <= 500) profileSettingState.biography = it
                        },
                        textStyle = TextStyle(
                            color = colorResource(id = R.color.primary_text_color),
                            fontWeight = FontWeight.Medium,
                            fontFamily = PoppinsFamily,
                            fontSize = 11.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 125.dp)
                            .background(Color.Transparent)
                            .padding(bottom = 4.dp),
                        shape = RoundedCornerShape(7.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = colorResource(id = R.color.edit_text_background),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    Text(
                        text = "${profileSettingState.biography.length} / 500",
                        fontSize = 8.sp,
                        fontFamily = DmSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.primary_text_color),
                        modifier = Modifier
                            .padding(bottom = 12.dp, end = 14.dp)
                            .alpha(0.65f)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Website:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    TextField(
                        value = profileSettingState.website,
                        placeholder = {
                            Text(
                                text = "Website",
                                color = colorResource(id = R.color.primary_text_color),
                                fontWeight = FontWeight.Medium,
                                fontFamily = PoppinsFamily,
                                fontSize = 11.sp,
                                modifier = Modifier.alpha(0.4f)
                            )
                        },
                        singleLine = true,
                        onValueChange = {
                            profileSettingState.website = it
                        },
                        textStyle = TextStyle(
                            color = colorResource(id = R.color.primary_text_color),
                            fontWeight = FontWeight.Medium,
                            fontFamily = PoppinsFamily,
                            fontSize = 11.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        shape = RoundedCornerShape(7.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = colorResource(id = R.color.edit_text_background),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    ClickableText(
                        text = "+ Other Social Media",
                        fontSize = 12.dp,
                        fontFamily = PoppinsFamily,
                        color = R.color.appPrimaryColor,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.1.dp,
                        modifier = Modifier,
                        onClick = profileSettingState.onOtherSocialMediaClick
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Phone Number:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {

//                    Spacer(
//                        modifier = Modifier
//                            .padding(start = 58.dp)
//                            .zIndex(1f)
//                            .height(20.dp)
//                            .width(1.dp)
//                            .background(color = colorResource(id = R.color.separator_color))
//                    )

                    TextField(
                        value = profileSettingState.phone,
                        singleLine = true,
                        onValueChange = {
                            if (it.isPhoneNumber())
                                profileSettingState.phone = it
                        },
                        textStyle = TextStyle(
                            color = colorResource(id = R.color.primary_text_color),
                            fontWeight = FontWeight.Medium,
                            fontFamily = PoppinsFamily,
                            fontSize = 11.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        shape = RoundedCornerShape(7.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = colorResource(id = R.color.edit_text_background),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                }
                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Location:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = profileSettingState.location,
                    placeholder = {
                        Text(
                            text = "Address",
                            color = colorResource(id = R.color.primary_text_color),
                            fontWeight = FontWeight.Medium,
                            fontFamily = PoppinsFamily,
                            fontSize = 11.sp,
                            modifier = Modifier.alpha(0.4f)
                        )
                    },
                    singleLine = true,
                    onValueChange = {
                        profileSettingState.location = it
                    },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.primary_text_color),
                        fontWeight = FontWeight.Medium,
                        fontFamily = PoppinsFamily,
                        fontSize = 11.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    shape = RoundedCornerShape(7.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.edit_text_background),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(120.dp))
            }
        }

        Column {
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 12.dp)
                    .background(colorResource(id = R.color.primary_background_color))
                    .padding(horizontal = 42.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                GradientButton(
                    text = "Save Changes",
                    radius = 100,
                    shadow = 2,
                    onClick = profileSettingState.onSaveClick
                )
            }
        }
    }
}
