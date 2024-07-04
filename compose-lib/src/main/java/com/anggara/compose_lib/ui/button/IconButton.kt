package com.anggara.compose_lib.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.anggara.compose_lib.theme.Neutral30
import com.anggara.compose_lib.theme.Neutral50
import com.anggara.compose_lib.theme.Neutral70
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.utils.clickableWithRipple

@Composable
fun IconButton(
    vector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    tint: Color = Color.Neutral70,
    backgroundColor: Color = Color.Neutral50,
    disableColor: Color = Color.Neutral30,
    isBorder: Boolean = false,
    size: Dp = space.x10,
    enabled: Boolean = true,
    borderWidth: Dp = space.buttonBorder,
    radius: Dp = space.x2,
    padding: Dp = space.x2,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(radius)
    val borderModifier = if (isBorder) {
        Modifier.border(
            width = borderWidth,
            color = if (enabled) tint else disableColor,
            shape = shape
        )
    } else {
        Modifier
    }

    Icon(
        imageVector = vector,
        tint = tint,
        contentDescription = contentDescription,
        modifier = modifier
            .clip(shape)
            .background(color = if (enabled) backgroundColor else disableColor)
            .then(borderModifier)
            .clickableWithRipple {
                if (!enabled) {
                    return@clickableWithRipple
                }
                onClick.invoke()
            }
            .size(size)
            .padding(padding)
    )
}