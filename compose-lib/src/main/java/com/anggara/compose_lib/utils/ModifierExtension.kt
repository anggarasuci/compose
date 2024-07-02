package com.anggara.compose_lib.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Modifier.clickableWithRipple(
    color: Color = Color.Transparent,
    onClick: () -> Unit
): Modifier {
    val coroutineScope = rememberCoroutineScope()
    return composed {
        this.clickable(
            indication = rememberRipple(
                bounded = true,
                color = color.takeOrElse {
                    LocalContentColor.current.copy(
                        alpha = LocalContentColor.current.alpha
                    )
                }
            ),
            interactionSource = remember { MutableInteractionSource() }) {
            coroutineScope.launch {
                delay(100)
                onClick()
            }
        }
    }
}
