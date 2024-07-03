package com.anggara.compose_lib.ui.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    fontFamily: FontFamily? = jakartaSans,
) {
    val style = MaterialTheme.typography.bodySmall
    Text(
        text = text,
        overflow = overflow,
        style = style,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        lineHeight = lineHeight?.scaledSize()?.sp ?: TextUnit.Unspecified,
        fontSize = fontSize.scaledSize().sp,
        maxLines = maxLines,
        modifier = modifier,
        color = color,
        textAlign = textAlign
    )
}