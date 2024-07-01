package com.anggara.compose_lib.ui.bottom_sheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Scrim
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.text.TextBodyMediumBold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    bottomSheetState: SheetState,
    isShown: Boolean,
    modifier: Modifier = Modifier,
    title: String = "",
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    if (isShown) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            contentColor = Color.Neutral10,
            containerColor = Color.Neutral10,
            scrimColor = Color.Scrim,
            shape = RoundedCornerShape(topStart = space.x3, topEnd = space.x3),
            onDismissRequest = {
                coroutineScope.launch {
                    onDismiss.invoke()
                }
            },
            content = {
                BackHandler(enabled = bottomSheetState.isVisible) {
                    coroutineScope.launch { onDismiss.invoke() }
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = space.x6)
                ) {
                    if (title.isNotEmpty()) {
                        TextBodyMediumBold(
                            text = title,
                            modifier = Modifier.padding(horizontal = space.x4)
                        )
                        Spacer(modifier = Modifier.height(space.x4))
                    }
                    content()
                }
            }
        )
    }
}