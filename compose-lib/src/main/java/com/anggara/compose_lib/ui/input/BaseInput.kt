package com.anggara.compose_lib.ui.input

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anggara.compose_lib.theme.DangerMain
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Neutral20
import com.anggara.compose_lib.theme.Neutral40
import com.anggara.compose_lib.theme.Neutral70
import com.anggara.compose_lib.theme.Neutral90
import com.anggara.compose_lib.theme.jakartaSans
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.text.Text
import com.anggara.compose_lib.ui.text.TextBodySmallRegular
import com.anggara.compose_lib.utils.scaledSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BaseInput(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    enable: Boolean = true,
    isDecimalInput: Boolean = false,
    isNumberInput: Boolean = false,
    isPasswordInput: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = Color.Neutral90,
    tintColor: Color = Color.Neutral70,
    borderColor: Color = Color.Neutral40,
    backgroundColor: Color = Color.Neutral10,
    disableBackgroundColor: Color = Color.Neutral20,
    placeholderTextColor: Color = Color.Neutral40,
    fontSize: Float = MaterialTheme.typography.bodyMedium.fontSize.value,
    fontWeight: FontWeight = FontWeight.W500,
    fontFamily: FontFamily = jakartaSans,
    radius: Dp = space.x2,
    trailingIconResId: Int = 0,
    isNextSoftKeyboard: Boolean = false,
    isDoneSoftKeyboard: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onDone: (String) -> Unit = {},
    onClick: () -> Unit = {},
    onTrailingIconClick: () -> Unit = {},
) {
    var passwordVisibleState by remember {
        mutableStateOf(false)
    }

    val textStyle = MaterialTheme.typography.bodyMedium

    val visualTransformation: VisualTransformation =
        if (!passwordVisibleState && isPasswordInput) PasswordVisualTransformation()
        else VisualTransformation.None

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    BasicTextField(
        value = value,
        readOnly = !enable,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (!enable) disableBackgroundColor else backgroundColor
            )
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    coroutineScope.launch {
                        delay(200)
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
            .clickable { onClick.invoke() },
        textStyle = textStyle.copy(
            color = textColor,
            textAlign = textAlign,
            fontFamily = fontFamily,
            fontSize = fontSize.scaledSize().sp,
            fontWeight = fontWeight
        ),
        visualTransformation = visualTransformation,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onDone.invoke(value)
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = when {
                isPasswordInput -> KeyboardType.Password
                isNumberInput -> KeyboardType.Number
                else -> KeyboardType.Text
            },
            imeAction = when {
                isDoneSoftKeyboard -> ImeAction.Done
                isNextSoftKeyboard -> ImeAction.Next
                else -> ImeAction.Default
            }
        ),
        interactionSource = interactionSource,
        singleLine = singleLine,
        decorationBox = @Composable { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                placeholder = {
                    Text(
                        text = placeholder,
                        fontSize = fontSize,
                        fontFamily = fontFamily,
                        fontWeight = fontWeight,
                        color = placeholderTextColor
                    )
                },
                innerTextField = innerTextField,
                enabled = enable,
                isError = true,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(space.x3),
                trailingIcon = if (!isPasswordInput && trailingIconResId == 0) null else {
                    {
                        if (trailingIconResId != 0) {
                            Icon(
                                painter = painterResource(id = trailingIconResId),
                                contentDescription = placeholder,
                                tint = tintColor,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(space.x4))
                                    .clickable {
                                        onTrailingIconClick.invoke()
                                    }
                            )
                        }

                        if (isPasswordInput) {
                            IconButton(onClick = { passwordVisibleState = !passwordVisibleState }) {
                                Icon(
                                    imageVector = if (passwordVisibleState) Icons.Filled.Visibility
                                    else Icons.Filled.VisibilityOff,
                                    tint = tintColor,
                                    contentDescription = "password"
                                )
                            }
                        }
                    }
                },
                container = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                            .border(
                                width = 1.5.dp,
                                color = borderColor,
                                shape = RoundedCornerShape(radius)
                            )
                    )
                }
            )
        },
        onValueChange = { onValueChange.invoke(it) })
}

@Composable
fun InputView(
    label: String,
    errorMessage: String,
    modifier: Modifier = Modifier,
    errorTextColor: Color = Color.DangerMain,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            TextBodySmallRegular(text = label, modifier = Modifier.padding(horizontal = space.x1))
            Spacer(modifier = Modifier.height(space.x1))
        }

        content()

        if (errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(space.x1))
            TextBodySmallRegular(
                text = errorMessage,
                color = errorTextColor,
                modifier = Modifier.padding(horizontal = space.x3)
            )
        }
    }
}