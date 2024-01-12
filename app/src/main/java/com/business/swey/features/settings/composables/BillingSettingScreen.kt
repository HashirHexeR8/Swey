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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.business.swey.R
import com.business.swey.core.fonts.DmSansFamily
import com.business.swey.core.fonts.PoppinsFamily
import com.business.swey.core.views.GradientButton
import com.business.swey.core.views.RadioButtonWithLabel
import com.business.swey.features.settings.states.BillingSettingState

@Composable
fun BillingSettingScreen(billingSettingState: BillingSettingState) {

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
                Image(painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { billingSettingState.onDismiss() })


                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Billing",
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
                        ) { billingSettingState.onDismiss() }
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .clipToBounds()
                    .background(colorResource(id = R.color.secondary_separator_color))
            )

            Column(modifier = Modifier.padding(horizontal = 42.dp)) {

                Spacer(modifier = Modifier.height(14.dp))

                Row {
                    RadioButtonWithLabel(
                        selected = billingSettingState.isCompany.not(),
                        label = "Name",
                        selectedColorText = colorResource(id = R.color.appPrimaryColor),
                        unSelectedColorText = colorResource(id = R.color.primary_text_color),
                        selectedColor = Color(0xFF2E3A59),
                        unSelectedColor = Color(0xFF2E3A59),
                        onOptionSelected = {
                            if (it)
                                billingSettingState.isCompany = it.not()
                        }
                    )

                    Spacer(modifier = Modifier.width(21.dp))

                    RadioButtonWithLabel(
                        selected = billingSettingState.isCompany,
                        label = "Company",
                        selectedColorText = colorResource(id = R.color.appPrimaryColor),
                        unSelectedColorText = colorResource(id = R.color.primary_text_color),
                        selectedColor = Color(0xFF2E3A59),
                        unSelectedColor = Color(0xFF2E3A59),
                        onOptionSelected = {
                            if (it)
                                billingSettingState.isCompany = it
                        }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = billingSettingState.nameOrCompany,
                    singleLine = true,
                    onValueChange = {
                        billingSettingState.nameOrCompany = it
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
                    text = "Country:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = billingSettingState.country,
                    singleLine = true,
                    onValueChange = {
                        billingSettingState.country = it
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
                    text = "City:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = billingSettingState.city,
                    singleLine = true,
                    onValueChange = {
                        billingSettingState.city = it
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
                    text = "Address:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = billingSettingState.address,
                    singleLine = true,
                    onValueChange = {
                        billingSettingState.address = it
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
                    text = "Zip or postal code:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = billingSettingState.postalCode,
                    singleLine = true,
                    onValueChange = {
                        billingSettingState.postalCode = it
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
                    text = "State, province or region:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = billingSettingState.state,
                    singleLine = true,
                    onValueChange = {
                        billingSettingState.state = it
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
                    text = "Preferred shipping courier:",
                    fontSize = 12.sp,
                    fontFamily = DmSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color_title),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                TextField(
                    value = billingSettingState.preferredCourier,
                    singleLine = true,
                    onValueChange = {
                        billingSettingState.preferredCourier = it
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

            Spacer(modifier = Modifier.height(30.dp))
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
                    onClick = billingSettingState.onSaveClick
                )
            }
        }
    }
}
