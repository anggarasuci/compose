package com.anggara.compose_lib.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Space(
    val x1: Dp = 4.dp,
    val x2: Dp = 8.dp,
    val x3: Dp = 12.dp,
    val x4: Dp = 16.dp,
    val x5: Dp = 20.dp,
    val x6: Dp = 24.dp,
    val x7: Dp = 28.dp,
    val x8: Dp = 32.dp,
    val x9: Dp = 36.dp,
    val x10: Dp = 40.dp,
    val x11: Dp = 44.dp,
    val x12: Dp = 48.dp,
    val x13: Dp = 52.dp,
    val x14: Dp = 56.dp,
    val x15: Dp = 60.dp,
    val x16: Dp = 64.dp,
)

val LocalSpace = compositionLocalOf { Space() }
val space: Space
    @Composable
    @ReadOnlyComposable
    get() = LocalSpace.current
