package com.anggara.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.anggara.compose_lib.ui.input.InputNumber
import com.anggara.compose_lib.ui.input.InputPassword
import com.anggara.compose_lib.ui.input.InputText
import com.anggara.compose_lib.ui.signature.SignatureView
import com.anggara.compose_lib.ui.text.TextBodyMediumBold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var state by remember {
                mutableStateOf("menu")
            }
            ComposeComponentTheme {
                when (state) {
                    "menu" -> Menu(onClick = { state = it })
                    "input" -> InputPage(onBack = { state = "menu" })
                    "signature" -> SignaturePage(onBack = { state = "menu" })
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
            .padding(horizontal = space.x2, vertical = space.x4)
            .fillMaxSize()
    ) {
        TextBodyMediumBold(text = "Menu", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(space.x2))
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
        }
    }

}

@Composable
fun BaseScreen(onBack: () -> Unit, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = space.x2, vertical = space.x4)
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
        Spacer(modifier = Modifier.height(space.x5))

        InputPassword(
            label = "Password",
            placeholder = "Input Password",
            value = statePassword,
            onValueChange = { statePassword = it })
        Spacer(modifier = Modifier.height(space.x5))

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