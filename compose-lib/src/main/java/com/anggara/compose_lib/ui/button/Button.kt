package com.anggara.compose_lib.ui.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun BaseButton(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isBorderShown: Boolean = true,
    backgroundColor: Color = Color.Neutral10,
    borderColor: Color = Color.Neutral40,
    textColor: Color = Color.Neutral70,
    contentPadding: PaddingValues = PaddingValues(vertical = space.x2, horizontal = space.x3),
    radius: Dp = space.x2,
    borderWidth: Dp = space.buttonBorder,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val color = ButtonDefaults.buttonColors().copy(
        containerColor = backgroundColor,
        contentColor = textColor
    )
    val border = BorderStroke(width = borderWidth, color = borderColor)
    val shape = RoundedCornerShape(radius)
    androidx.compose.material3.Button(
        onClick = {
            if (isLoading) {
                return@Button
            }
            onClick.invoke()
        },
        modifier = modifier,
        colors = color,
        enabled = enabled,
        border = if (isBorderShown) border else null,
        shape = shape,
        contentPadding = contentPadding
    ) {
        content()
    }
}

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.W500,
    enabled: Boolean = true,
    isBorderShown: Boolean = true,
    isLoading: Boolean = false,
    backgroundColor: Color = Color.Neutral10,
    borderColor: Color = Color.Neutral40,
    textColor: Color = Color.Neutral70,
    contentPadding: PaddingValues = PaddingValues(vertical = space.x2, horizontal = space.x3),
    radius: Dp = space.x2,
    borderWidth: Dp = space.buttonBorder,
    onClick: () -> Unit,
) {

    BaseButton(
        isLoading = isLoading,
        modifier = modifier,
        enabled = enabled,
        isBorderShown = isBorderShown,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        textColor = textColor,
        contentPadding = contentPadding,
        radius = radius,
        borderWidth = borderWidth,
        onClick = { onClick.invoke() }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center) {
                this@Row.AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CircularProgressIndicator(
                        color = textColor,
                        modifier = Modifier
                            .size(space.x6.plus(space.x1 / 2))
                    )
                }

                this@Row.AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    TextBodyMedium(
                        text = text,
                        maxLines = 1,
                        fontWeight = fontWeight,
                        color = textColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun Button(
    text: String,
    fontSize: Float,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.W500,
    enabled: Boolean = true,
    isBorderShown: Boolean = true,
    isLoading: Boolean = false,
    backgroundColor: Color = Color.Neutral10,
    borderColor: Color = Color.Neutral40,
    textColor: Color = Color.Neutral70,
    contentPadding: PaddingValues = PaddingValues(vertical = space.x2, horizontal = space.x3),
    radius: Dp = space.x2,
    borderWidth: Dp = space.buttonBorder,
    fontFamily: FontFamily? = null,
    lineHeight: Float? = null,
    onClick: () -> Unit,
) {
    BaseButton(
        isLoading = isLoading,
        modifier = modifier,
        enabled = enabled,
        isBorderShown = isBorderShown,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        textColor = textColor,
        contentPadding = contentPadding,
        radius = radius,
        borderWidth = borderWidth,
        onClick = { onClick.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center) {
                this@Row.AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CircularProgressIndicator(
                        color = textColor,
                        modifier = Modifier
                            .size(lineHeight?.scaledSize()?.dp ?: fontSize.scaledSize().dp)

                    )
                }
                this@Row.AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = text,
                        maxLines = 1,
                        fontSize = fontSize,
                        fontWeight = fontWeight,
                        fontFamily = fontFamily,
                        color = textColor,
                        textAlign = TextAlign.Center,
                        lineHeight = lineHeight
                    )
                }
            }
        }
    }
}