package com.anggara.compose_lib.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp

@Composable
private fun getScaleFactor(): Float {
    val configuration = LocalConfiguration.current
    //val density = Density(configuration.densityDpi / 160f)
    val screenWidthDp = configuration.screenWidthDp

    return when {
        screenWidthDp > 800 -> 1.3f // xLarge
        screenWidthDp > 600 -> 1.15f // Large
        else -> 1.0f // Normal
    } //* LocalDensity.current.density
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
