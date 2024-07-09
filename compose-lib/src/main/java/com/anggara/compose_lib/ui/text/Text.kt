package com.anggara.compose_lib.ui.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.anggara.compose_lib.theme.Neutral90
import com.anggara.compose_lib.theme.jakartaSans
import com.anggara.compose_lib.utils.scaledSize

@Composable
fun Text(
    text: String,
    fontSize: Float,
    modifier: Modifier = Modifier,
    lineHeight: Float? = null,
    fontWeight: FontWeight = FontWeight.Normal,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Neutral90,
    textAlign: TextAlign? = null,
    withTabular: Boolean = false,
    fontFamily: FontFamily? = jakartaSans,
) {
    /**
     * fontFeatureSettings - The advanced typography settings provided by font.
     * The format is the same as the CSS font-feature-settings attribute:
     * https:// www. w3.org/ TR/ css-fonts-3/#font-feature-settings-prop
     */
    val style = TextStyle(
        fontSize = fontSize.scaledSize().sp,
        fontWeight = fontWeight,
        lineHeight = lineHeight?.scaledSize()?.sp ?: TextUnit.Unspecified,
        color = color,
        fontFamily = fontFamily,
        textAlign = textAlign ?: TextAlign.Unspecified,
        fontFeatureSettings = if (withTabular) "tnum" else null
    )

    Text(
        text = text,
        overflow = overflow,
        style = style,
        maxLines = maxLines,
        modifier = modifier,
    )
}