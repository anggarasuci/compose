package com.anggara.compose_lib.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp

enum class ScaleFactor(val value: Float) {
    X_LARGE(1.3f),
    LARGE(1.15f),
    NORMAL(1.0f)
}

@Composable
private fun getScaleFactor(): Float {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    return when {
        screenWidthDp > 800 -> ScaleFactor.X_LARGE.value
        screenWidthDp > 600 -> ScaleFactor.LARGE.value
        else -> ScaleFactor.NORMAL.value
    }
}

@Composable
fun Float.scaledSize(): Float {
    val scaleFactor = getScaleFactor()
    return this * scaleFactor
}

@Composable
fun Dp.scaledDp(): Dp {
    val scaleFactor = getScaleFactor()
    return this * scaleFactor
}
