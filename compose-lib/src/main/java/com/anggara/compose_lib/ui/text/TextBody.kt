package com.anggara.compose_lib.ui.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.anggara.compose_lib.theme.Neutral90

@Composable
fun TextBodySmallRegular(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Neutral90,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Normal,
        maxLines = maxLines,
        modifier = modifier.fillMaxWidth(),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun TextBodySmallBold(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Neutral90,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        maxLines = maxLines,
        modifier = modifier.fillMaxWidth(),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun TextBodyMediumRegular(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Neutral90,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Normal,
        maxLines = maxLines,
        modifier = modifier.fillMaxWidth(),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun TextBodyMediumBold(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Neutral90,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        maxLines = maxLines,
        modifier = modifier.fillMaxWidth(),
        color = color,
        textAlign = textAlign
    )
}
