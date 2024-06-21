package com.anggara.compose_lib.ui.input

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.anggara.compose_lib.theme.DangerMain
import com.anggara.compose_lib.theme.Neutral40

@Composable
fun InputNumber(
    label: String,
    placeholder: String,
    value: String,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Neutral40,
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
            onValueChange = { onValueChange.invoke(it) })
    }
}