package com.anggara.compose_lib.ui.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Neutral40
import com.anggara.compose_lib.theme.Neutral70
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.text.Text
import com.anggara.compose_lib.ui.text.TextBodyMedium

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.W500,
    enabled: Boolean = true,
    isBorderShown: Boolean = true,
    backgroundColor: Color = Color.Neutral10,
    borderColor: Color = Color.Neutral40,
    textColor: Color = Color.Neutral70,
    contentPadding: PaddingValues = PaddingValues(vertical = space.x2, horizontal = space.x3),
    radius: Dp = space.x2,
    borderWidth: Dp = space.buttonBorder,
    onClick: () -> Unit,
) {
    val color = ButtonDefaults.buttonColors().copy(
        containerColor = backgroundColor,
        contentColor = textColor
    )
    val border = BorderStroke(width = borderWidth, color = borderColor)
    val shape = RoundedCornerShape(radius)
    androidx.compose.material3.Button(
        onClick = { onClick.invoke() },
        modifier = modifier,
        colors = color,
        enabled = enabled,
        border = if (isBorderShown) border else null,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBodyMedium(
                text = text,
                fontWeight = fontWeight,
                modifier = Modifier.weight(1f),
                color = textColor,
                textAlign = TextAlign.Center
            )
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
    val color = ButtonDefaults.buttonColors().copy(
        containerColor = backgroundColor,
        contentColor = textColor
    )
    val border = BorderStroke(width = borderWidth, color = borderColor)
    val shape = RoundedCornerShape(radius)
    androidx.compose.material3.Button(
        onClick = { onClick.invoke() },
        modifier = modifier,
        colors = color,
        enabled = enabled,
        border = if (isBorderShown) border else null,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = fontSize,
                modifier = Modifier.weight(1f),
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                color = textColor,
                textAlign = TextAlign.Center,
                lineHeight = lineHeight
            )
        }
    }
}