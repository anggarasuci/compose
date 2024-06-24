package com.anggara.compose_lib.ui.signature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.anggara.compose_lib.theme.DangerMain
import com.anggara.compose_lib.theme.DangerSurface
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.button.IconButton
import io.github.joelkanyi.sain.Sain
import io.github.joelkanyi.sain.SignatureState


@Composable
private fun SignaturePad(
    state: SignatureState,
    height: Int = 250,
) {
    Sain(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .border(
                BorderStroke(
                    width = .5.dp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        onComplete = { },
    ) { }
}

@Composable
fun SignatureView(
    modifier: Modifier = Modifier,
    state: SignatureState = SignatureState(),
) {
    Column {
        Box(modifier = modifier.fillMaxSize()) {
            SignaturePad(state = state)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(space.x2)
            ) {
                AnimatedVisibility(
                    visible = state.signatureLines.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(
                        vector = Icons.Outlined.Delete,
                        backgroundColor = Color.DangerSurface,
                        tint = Color.DangerMain,
                        isBorder = true
                    ) {
                        state.clearSignatureLines()

                    }
                }
            }

        }
    }
}
