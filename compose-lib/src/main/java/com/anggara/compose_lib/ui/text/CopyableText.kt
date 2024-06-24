package com.anggara.compose_lib.ui.text

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString

@Composable
fun CopyableText(text: String, content: @Composable (text: String, modifier: Modifier) -> Unit) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context: Context = LocalContext.current

    content(
        text,
        Modifier.clickable {
            clipboardManager.setText(AnnotatedString(text))
            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    )
}