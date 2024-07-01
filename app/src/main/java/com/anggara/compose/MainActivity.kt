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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anggara.compose.ui.theme.ComposeComponentTheme
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.bottom_sheet.BottomSheetMultipleSelection
import com.anggara.compose_lib.ui.bottom_sheet.BottomSheetSelection
import com.anggara.compose_lib.ui.bottom_sheet.BottomSheetSelectionWithConfirmation
import com.anggara.compose_lib.ui.bottom_sheet.OptionModel
import com.anggara.compose_lib.ui.button.LeadingButton
import com.anggara.compose_lib.ui.button.LeadingTrailingButton
import com.anggara.compose_lib.ui.button.TrailingButton
import com.anggara.compose_lib.ui.input.InputNumber
import com.anggara.compose_lib.ui.input.InputPassword
import com.anggara.compose_lib.ui.input.InputText
import com.anggara.compose_lib.ui.signature.SignatureView
import com.anggara.compose_lib.ui.text.TextBodyMediumBold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //WindowCompat.setDecorFitsSystemWindows(window, false)
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
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
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
                onClick = { onClick.invoke("bottomSheet") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Bottom Sheet")
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
        InputText(
            label = "Name",
            isError = state == "error",
            errorMessage = if (state == "error") "Error Name Please Input Other Name" else "",
            placeholder = "Input Name, type `error` for trigger error for example only",
            value = state,
            onValueChange = { state = it })
        Spacer(modifier = Modifier.height(space.x10))

        InputPassword(
            label = "Password",
            placeholder = "Input Password",
            value = statePassword,
            onValueChange = { statePassword = it })
        Spacer(modifier = Modifier.height(space.x10))

        InputNumber(
            label = "Number",
            placeholder = "Input Number",
            value = stateNumber,
            onValueChange = { stateNumber = it })
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
    BaseScreen(onBack = onBack) {
        LeadingTrailingButton(
            text = "Test",
            leadingIconVector = Icons.Filled.AddAPhoto,
            trailingIconVector = Icons.Default.ChevronRight,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(space.x10))
        LeadingButton(text = "Tes 2", iconVector = Icons.Filled.AddAPhoto) { }
        Spacer(modifier = Modifier.height(space.x10))
        TrailingButton(text = "Tes 3", iconVector = Icons.Filled.AddAPhoto) { }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
                value = "1",
                name = "Option 1",
                description = "Description 1"
            ),
            OptionModel(
                value = "2",
                name = "Option 2",
                description = "Description 2"
            ),
            OptionModel(
                value = "3",
                name = "Option 3"
            ),
            OptionModel(
                value = "4",
                name = "Option 4",
                description = "Description 4"
            ),
            OptionModel(
                value = "5",
                name = "Option 5"
            ),
            OptionModel(
                value = "6",
                name = "Option 6"
            ),
            OptionModel(
                value = "7",
                name = "Option 7"
            ),
            OptionModel(
                value = "8",
                name = "Option 8"
            ),
            OptionModel(
                value = "9",
                name = "Option 9"
            ),
            OptionModel(
                value = "10",
                name = "Option 10"
            ),
            OptionModel(
                value = "11",
                name = "Option 11"
            ),
        )

        TrailingButton(
            text = "Show Default Bottom Sheet",
            iconVector = Icons.Default.ChevronRight,
            onClick = {
                defaultIsShown = true
            }
        )
        Spacer(modifier = Modifier.height(space.x10))
        TrailingButton(
            text = selectedValue.ifEmpty { "Show Selection Bottom Sheet" },
            iconVector = Icons.Default.ChevronRight,
            onClick = {
                selectionIsShown = true
            }
        )

        Spacer(modifier = Modifier.height(space.x10))
        TrailingButton(
            text = selectedValue.ifEmpty { "Show Selection with Confirm Bottom Sheet" },
            iconVector = Icons.Default.ChevronRight,
            onClick = {
                selectionConfirmIsShown = true
            }
        )

        Spacer(modifier = Modifier.height(space.x10))
        TrailingButton(
            text = selectedValues.joinToString(",")
                .ifEmpty { "Multiple Selection with Confirm Bottom Sheet" },
            iconVector = Icons.Default.ChevronRight,
            onClick = {
                multipleSelectionConfirmIsShown = true
            }
        )

//        if (defaultIsShown) {
//            BottomSheet(isShown = defaultIsShown, title = "Default Bottom Sheet", onDismiss = {
//                defaultIsShown = false
//            }) { }
//        }

        BottomSheetSelection(
            isShown = selectionIsShown,
            selectedValue = selectedValue,
            title = "Selection Bottom Sheet",
            data = items,
            onSelect = { selectedValue = it },
            onDismiss = { selectionIsShown = false }
        )

        BottomSheetSelectionWithConfirmation(
            isShown = selectionConfirmIsShown,
            selectedValue = selectedValue,
            title = "Selection with Confirm Bottom Sheet",
            data = items,
            onOk = { selectedValue = it },
            onCancel = { },
            onDismiss = { selectionConfirmIsShown = false }
        )

        BottomSheetMultipleSelection(
            isShown = multipleSelectionConfirmIsShown,
            selectedValues = selectedValues,
            title = "Multiple Selection",
            data = items,
            onOk = { selectedValues = it },
            onCancel = { },
            onDismiss = { multipleSelectionConfirmIsShown = false }
        )
    }
}