package com.anggara.compose_lib.ui.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Neutral40
import com.anggara.compose_lib.theme.Neutral70
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.text.Text
import com.anggara.compose_lib.ui.text.TextBodyMedium
import com.anggara.compose_lib.utils.scaledSize

@Composable
fun TrailingButton(
    text: String,
    iconResId: Int,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.W500,
    enabled: Boolean = true,
    backgroundColor: Color = Color.Neutral10,
    borderColor: Color = Color.Neutral40,
    textColor: Color = Color.Neutral70,
    size: Dp = space.x8,
    iconColor: Color = Color.Neutral70,
    radius: Dp = space.x2,
    contentPadding: PaddingValues = PaddingValues(vertical = space.x2, horizontal = space.x3),
    borderWidth: Dp = space.buttonBorder,
    fontSize: Float = MaterialTheme.typography.bodyMedium.fontSize.value,
    textAlign: TextAlign = TextAlign.Center,
    isLoading: Boolean = false,
    lineHeight: Float? = null,
    fontFamily: FontFamily? = null,
    onClick: () -> Unit
) {
    BaseButton(
        isLoading = isLoading,
        modifier = modifier,
        backgroundColor = backgroundColor,
        textColor = textColor,
        enabled = enabled,
        borderColor = borderColor,
        borderWidth = borderWidth,
        radius = radius,
        contentPadding = contentPadding,
        onClick = { onClick.invoke() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                this@Row.AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CircularProgressIndicator(
                        color = textColor,
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .padding(vertical = space.x1 / 2)
                            .size(lineHeight?.scaledSize()?.dp ?: fontSize.scaledSize().dp)
                    )
                }

                this@Row.AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            maxLines = 1,
                            fontSize = fontSize,
                            fontWeight = fontWeight,
                            fontFamily = fontFamily,
                            color = textColor,
                            textAlign = textAlign,
                            lineHeight = lineHeight,
                            modifier = Modifier.weight(1f),
                        )
                        Spacer(modifier = Modifier.width(space.x2))
                        Icon(
                            painter = painterResource(id = iconResId),
                            contentDescription = text,
                            tint = iconColor,
                            modifier = Modifier
                                .size(size)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TrailingButton(
    text: String,
    iconVector: ImageVector,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.W500,
    enabled: Boolean = true,
    backgroundColor: Color = Color.Neutral10,
    borderColor: Color = Color.Neutral40,
    textColor: Color = Color.Neutral70,
    size: Dp = space.x8,
    iconColor: Color = Color.Neutral70,
    radius: Dp = space.x2,
    contentPadding: PaddingValues = PaddingValues(vertical = space.x2, horizontal = space.x3),
    borderWidth: Dp = space.buttonBorder,
    fontSize: Float = MaterialTheme.typography.bodyMedium.fontSize.value,
    textAlign: TextAlign = TextAlign.Center,
    isLoading: Boolean = false,
    lineHeight: Float? = null,
    fontFamily: FontFamily? = null,
    onClick: () -> Unit
) {
    BaseButton(
        isLoading = isLoading,
        modifier = modifier,
        backgroundColor = backgroundColor,
        textColor = textColor,
        enabled = enabled,
        borderColor = borderColor,
        borderWidth = borderWidth,
        radius = radius,
        contentPadding = contentPadding,
        onClick = { onClick.invoke() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                this@Row.AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CircularProgressIndicator(
                        color = textColor,
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .padding(vertical = space.x1 / 2)
                            .size(lineHeight?.scaledSize()?.dp ?: fontSize.scaledSize().dp)
                    )
                }

                this@Row.AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            maxLines = 1,
                            fontSize = fontSize,
                            fontWeight = fontWeight,
                            fontFamily = fontFamily,
                            color = textColor,
                            textAlign = textAlign,
                            lineHeight = lineHeight,
                            modifier = Modifier.weight(1f),
                        )
                        Spacer(modifier = Modifier.width(space.x2))
                        Icon(
                            imageVector = iconVector,
                            contentDescription = text,
                            tint = iconColor,
                            modifier = Modifier
                                .size(size)
                        )
                    }
                }
            }
        }
    }
}