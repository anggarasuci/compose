package com.anggara.compose_lib.ui.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Neutral40
import com.anggara.compose_lib.theme.Neutral70
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.text.TextBodyMedium

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
    onClick: () -> Unit
) {
    val color = ButtonDefaults.buttonColors().copy(
        containerColor = backgroundColor,
        contentColor = textColor
    )
    val border = BorderStroke(width = space.x1 / 2, color = borderColor)
    val shape = RoundedCornerShape(radius)
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier,
        colors = color,
        enabled = enabled,
        border = border,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBodyMedium(
                text = text,
                fontWeight = fontWeight,
                modifier = Modifier.weight(1f),
                color = textColor
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
    onClick: () -> Unit
) {
    val color = ButtonDefaults.buttonColors().copy(
        containerColor = backgroundColor,
        contentColor = textColor
    )
    val border = BorderStroke(width = space.x1 / 2, color = borderColor)
    val shape = RoundedCornerShape(radius)
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier,
        colors = color,
        enabled = enabled,
        border = border,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBodyMedium(
                text = text,
                fontWeight = fontWeight,
                modifier = Modifier.weight(1f),
                color = textColor
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