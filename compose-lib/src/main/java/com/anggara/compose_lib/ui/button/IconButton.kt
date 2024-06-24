package com.anggara.compose_lib.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.anggara.compose_lib.theme.Neutral30
import com.anggara.compose_lib.theme.Neutral50
import com.anggara.compose_lib.theme.Neutral70
import com.anggara.compose_lib.theme.space

@Composable
fun IconButton(
    vector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    tint: Color = Color.Neutral70,
    backgroundColor: Color = Color.Neutral50,
    disableColor: Color = Color.Neutral30,
    isBorder: Boolean = false,
    size: Dp = space.x5,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(space.x1)
    Icon(
        imageVector = vector,
        tint = tint,
        contentDescription = contentDescription,
        modifier = modifier
            .clip(shape)
            .background(color = if (enabled) backgroundColor else disableColor)
            .border(
                width = 1.dp,
                color = if (isBorder && enabled) tint else disableColor,
                shape = shape
            )
            .clickable {
                if (!enabled) {
                    return@clickable
                }
                onClick.invoke()
            }
            .size(size)
            .padding(space.x1)
    )
}