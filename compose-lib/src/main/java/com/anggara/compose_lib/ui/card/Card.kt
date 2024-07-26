package com.anggara.compose_lib.ui.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.space

@Composable
fun CardContainer(
    modifier: Modifier = Modifier,
    radius: Dp = space.x2,
    contentPadding: PaddingValues = PaddingValues(space.x4),
    backgroundColor: Color = Color.Neutral10,
    borderColor: Color? = null,
    elevation: CardElevation = CardDefaults.cardElevation(),
    content: @Composable () -> Unit
) {
    val colors = CardDefaults.cardColors().copy(
        containerColor = backgroundColor
    )
    val borderStroke = BorderStroke(
        width = 1.dp,
        color = borderColor ?: Color.Unspecified
    )
    Card(
        modifier = modifier,
        colors = colors,
        border = borderStroke,
        shape = RoundedCornerShape(radius),
        elevation = elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        ) {
            content()
        }
    }
}