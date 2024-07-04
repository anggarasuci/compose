package com.anggara.compose_lib.ui.input

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.anggara.compose_lib.theme.DangerMain
import com.anggara.compose_lib.theme.Neutral40
import com.anggara.compose_lib.theme.Neutral90
import com.anggara.compose_lib.theme.jakartaSans

@Composable
fun InputNumber(
    label: String,
    placeholder: String,
    value: String,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Neutral40,
    textColor: Color = Color.Neutral90,
    placeholderTextColor: Color = Color.Neutral40,
    fontFamily: FontFamily = jakartaSans,
    fontWeight: FontWeight = FontWeight.W500,
    fontSize: Float = MaterialTheme.typography.bodyMedium.fontSize.value,
    isError: Boolean = false,
    errorMessage: String = "",
    onValueChange: (String) -> Unit
) {
    InputView(label = label, isError = isError, errorMessage = errorMessage, modifier = modifier) {
        BaseInput(
            isNumberInput = true,
            value = value,
            placeholder = placeholder,
            borderColor = if (isError) Color.DangerMain else borderColor,
            textColor = textColor,
            placeholderTextColor = placeholderTextColor,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize,
            onValueChange = { onValueChange.invoke(it) })
    }
}