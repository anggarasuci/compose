package com.anggara.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.anggara.compose.ui.theme.ComposeComponentTheme
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Neutral20
import com.anggara.compose_lib.theme.Neutral30
import com.anggara.compose_lib.theme.PrimaryMain
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.bottom_sheet.BottomSheetMultipleSelection
import com.anggara.compose_lib.ui.bottom_sheet.BottomSheetSelection
import com.anggara.compose_lib.ui.bottom_sheet.BottomSheetSelectionWithConfirmation
import com.anggara.compose_lib.ui.bottom_sheet.OptionModel
import com.anggara.compose_lib.ui.button.IconButton
import com.anggara.compose_lib.ui.button.LeadingButton
import com.anggara.compose_lib.ui.button.LeadingTrailingButton
import com.anggara.compose_lib.ui.button.TrailingButton
import com.anggara.compose_lib.ui.card.CardContainer
import com.anggara.compose_lib.ui.expandable.ExpandableView
import com.anggara.compose_lib.ui.input.InputNumber
import com.anggara.compose_lib.ui.input.InputPassword
import com.anggara.compose_lib.ui.input.InputText
import com.anggara.compose_lib.ui.signature.SignatureView
import com.anggara.compose_lib.ui.text.TextBodyMediumBold
import com.anggara.compose_lib.ui.text.TextBodyMediumRegular
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            var state by remember {
                mutableStateOf("menu")
            }
            ComposeComponentTheme {
                when (state) {
                    "menu" -> Menu(onClick = { state = it })
                    "input" -> InputPage(onBack = { state = "menu" })
                    "signature" -> SignaturePage(onBack = { state = "menu" })
                    "button" -> ButtonPage(onBack = { state = "menu" })
                    "bottomSheet" -> BottomSheetPage(onBack = { state = "menu" })
                    "expandable" -> ExpandablePage(onBack = { state = "menu" })
                    "card" -> CardPage(onBack = { state = "menu" })
                }
            }
        }
    }
}

@Composable
fun Menu(
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = space.x4, vertical = space.x8)
            .fillMaxSize()
    ) {
        TextBodyMediumBold(text = "Menu", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(space.x4))
        Column(
            modifier = Modifier.verticalScroll(state = rememberScrollState())
        ) {
            Button(onClick = { onClick.invoke("input") }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Input Text")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onClick.invoke("signature") }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Signature")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onClick.invoke("button") }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Button")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onClick.invoke("bottomSheet") }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Bottom Sheet")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onClick.invoke("expandable") }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Expandable")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onClick.invoke("card") }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Card")
            }
        }
    }

}

@Composable
fun BaseScreen(onBack: () -> Unit, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = space.x4, vertical = space.x8)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        content()
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onBack.invoke() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Back to Menu")
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun InputPage(onBack: () -> Unit) {
    var state by remember {
        mutableStateOf("")
    }

    var statePassword by remember {
        mutableStateOf("")
    }

    var stateNumber by remember {
        mutableStateOf("")
    }

    BaseScreen(onBack = onBack) {
        InputPassword(label = "Password",
            placeholder = "Input Password",
            value = statePassword,
            isNextSoftKeyboard = true,
            onValueChange = { statePassword = it })
        Spacer(modifier = Modifier.height(space.x10))

        InputText(label = "Name",
            isError = state == "error",
            errorMessage = if (state == "error") "Error Name Please Input Other Name" else "",
            placeholder = "Input Name, type `error` for trigger error for example only",
            value = state,
            isNextSoftKeyboard = true,
            onValueChange = { state = it })
        Spacer(modifier = Modifier.height(space.x10))

        InputNumber(label = "Number",
            placeholder = "Input Number",
            value = stateNumber,
            isDoneSoftKeyboard = true,
            onValueChange = { stateNumber = it })

        Spacer(modifier = Modifier.height(space.x10))

        IconButton(vector = Icons.Default.ChevronLeft,
            borderWidth = 0.dp,
            tint = Color.PrimaryMain,
            size = 60.dp,
            radius = space.x10,
            padding = 0.dp,
            backgroundColor = Color.Unspecified,
            onClick = {})
    }
}

@Composable
fun SignaturePage(onBack: () -> Unit) {
    BaseScreen(onBack = onBack) {
        SignatureView()
    }
}

@Composable
fun ButtonPage(onBack: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }
    BaseScreen(onBack = onBack) {
        LeadingTrailingButton(text = "Test",
            leadingIconVector = Icons.Filled.AddAPhoto,
            trailingIconVector = Icons.Default.ChevronRight,
            onClick = {})
        Spacer(modifier = Modifier.height(space.x10))
        LeadingButton(text = "Tes 2",
            iconVector = Icons.Filled.AddAPhoto,
            isLoading = isLoading,
            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight.value,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize.value,
            onClick = {
                isLoading = true
                coroutineScope.launch {
                    delay(3000)
                    isLoading = false
                }
            })
        Spacer(modifier = Modifier.height(space.x10))
        TrailingButton(text = "Tes 3",
            iconVector = Icons.Filled.AddAPhoto,
            isLoading = isLoading,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight.value,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize.value,
            onClick = {
                isLoading = true
                coroutineScope.launch {
                    delay(3000)
                    isLoading = false
                }
            })
        Spacer(modifier = Modifier.height(space.x10))
        com.anggara.compose_lib.ui.button.Button(text = "text", isLoading = isLoading, onClick = {
            isLoading = true
            coroutineScope.launch {
                delay(3000)
                isLoading = false
            }
        })

        Spacer(modifier = Modifier.height(space.x10))
        com.anggara.compose_lib.ui.button.Button(text = "button display large asdsadas dasd adasd ada dada dsa asasd asd asd asda ad sdad",
            fontSize = MaterialTheme.typography.displayLarge.fontSize.value,
            lineHeight = MaterialTheme.typography.displayLarge.lineHeight.value,
            isLoading = isLoading,
            onClick = {
                isLoading = true
                coroutineScope.launch {
                    delay(3000)
                    isLoading = false
                }
            })
    }
}

@Composable
fun BottomSheetPage(onBack: () -> Unit) {
    BaseScreen(onBack = onBack) {
        var defaultIsShown by remember {
            mutableStateOf(false)
        }
        var selectionIsShown by remember {
            mutableStateOf(false)
        }
        var selectedValue by remember {
            mutableStateOf("")
        }
        var selectionConfirmIsShown by remember {
            mutableStateOf(false)
        }
        var multipleSelectionConfirmIsShown by remember {
            mutableStateOf(false)
        }
        var selectedValues by remember {
            mutableStateOf<List<String>>(listOf())
        }


        val items: List<OptionModel> = listOf(
            OptionModel(
                value = "1", name = "Option 1", description = "Description 1"
            ),
            OptionModel(
                value = "2", name = "Option 2", description = "Description 2"
            ),
            OptionModel(
                value = "3", name = "Option 3"
            ),
            OptionModel(
                value = "4", name = "Option 4", description = "Description 4"
            ),
            OptionModel(
                value = "5", name = "Option 5"
            ),
            OptionModel(
                value = "6", name = "Option 6"
            ),
            OptionModel(
                value = "7", name = "Option 7"
            ),
            OptionModel(
                value = "8", name = "Option 8"
            ),
            OptionModel(
                value = "9", name = "Option 9"
            ),
            OptionModel(
                value = "10", name = "Option 10"
            ),
            OptionModel(
                value = "11", name = "Option 11"
            ),
        )

        TrailingButton(text = "Show Default Bottom Sheet",
            iconVector = Icons.Default.ChevronRight,
            onClick = {
                defaultIsShown = true
            })
        Spacer(modifier = Modifier.height(space.x10))
        TrailingButton(text = selectedValue.ifEmpty { "Show Selection Bottom Sheet" },
            iconVector = Icons.Default.ChevronRight,
            onClick = {
                selectionIsShown = true
            })

        Spacer(modifier = Modifier.height(space.x10))
        TrailingButton(text = selectedValue.ifEmpty { "Show Selection with Confirm Bottom Sheet" },
            iconVector = Icons.Default.ChevronRight,
            onClick = {
                selectionConfirmIsShown = true
            })

        Spacer(modifier = Modifier.height(space.x10))
        TrailingButton(text = selectedValues.joinToString(",")
            .ifEmpty { "Multiple Selection with Confirm Bottom Sheet" },
            iconVector = Icons.Default.ChevronRight,
            onClick = {
                multipleSelectionConfirmIsShown = true
            })

//        if (defaultIsShown) {
//            BottomSheet(isShown = defaultIsShown, title = "Default Bottom Sheet", onDismiss = {
//                defaultIsShown = false
//            }) { }
//        }

        BottomSheetSelection(isShown = selectionIsShown,
            selectedValue = selectedValue,
            title = "Selection Bottom Sheet",
            data = items,
            onSelect = { selectedValue = it },
            onDismiss = { selectionIsShown = false })

        BottomSheetSelectionWithConfirmation(isShown = selectionConfirmIsShown,
            selectedValue = selectedValue,
            title = "Selection with Confirm Bottom Sheet",
            data = items,
            onOk = { selectedValue = it },
            onCancel = { },
            onDismiss = { selectionConfirmIsShown = false })

        BottomSheetMultipleSelection(isShown = multipleSelectionConfirmIsShown,
            selectedValues = selectedValues,
            title = "Multiple Selection",
            data = items,
            onOk = { selectedValues = it },
            onCancel = { },
            onDismiss = { multipleSelectionConfirmIsShown = false })
    }
}

@Composable
fun ExpandablePage(onBack: () -> Unit) {
    BaseScreen(onBack = onBack) {
        ExpandableView(expandableHoverColor = Color.Neutral10,
            headContainerModifier = Modifier.padding(space.x2),
            headContent = {
                TextBodyMediumBold(
                    text = "Expandable", modifier = Modifier.fillMaxWidth()
                )
            }) {
            TextBodyMediumRegular(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sem viverra aliquet eget sit amet. Nec dui nunc mattis enim ut. Cursus eget nunc scelerisque viverra mauris in aliquam sem fringilla. Aliquet risus feugiat in ante metus dictum. Molestie a iaculis at erat.")
        }
    }
}

@Composable
fun CardPage(onBack: () -> Unit) {
    BaseScreen(onBack = onBack) {
        CardContainer(borderColor = Color.Neutral30, backgroundColor = Color.Neutral20) {
            TextBodyMediumRegular(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sem viverra aliquet eget sit amet.")
        }
    }
}