package com.anggara.compose_lib.ui.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.anggara.compose_lib.theme.DangerBorder
import com.anggara.compose_lib.theme.Neutral40
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.text.TextBodySmallRegular

@Composable
fun InputText(
    label: String,
    placeholder: String,
    value: String,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Neutral40,
    isError: Boolean = false,
    errorMessage: String = "",
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        TextBodySmallRegular(text = label)
        Spacer(modifier = Modifier.height(space.x1))
        BaseInput(
            value = value,
            placeholder = placeholder,
            borderColor = if (isError) Color.DangerBorder else borderColor,
            onValueChange = { onValueChange.invoke(it) })
        if (errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(space.x1 / 2))
            TextBodySmallRegular(text = errorMessage, color = Color.DangerBorder)
        }
    }
}