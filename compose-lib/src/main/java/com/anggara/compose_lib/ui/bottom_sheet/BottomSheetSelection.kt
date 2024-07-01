package com.anggara.compose_lib.ui.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Neutral60
import com.anggara.compose_lib.theme.Neutral90
import com.anggara.compose_lib.theme.PrimaryMain
import com.anggara.compose_lib.theme.PrimarySurface
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.text.TextBodyMedium
import com.anggara.compose_lib.ui.text.TextBodyMediumRegular
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSelection(
    isShown: Boolean,
    title: String,
    data: List<OptionModel>,
    modifier: Modifier = Modifier,
    selectedValue: String = "",
    selectedTextColor: Color = Color.PrimaryMain,
    selectedBackgroundColor: Color = Color.PrimarySurface,
    onSelect: (value: String) -> Unit,
    onDismiss: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    BottomSheet(
        isShown = isShown,
        title = title,
        bottomSheetState = bottomSheetState,
        onDismiss = { onDismiss.invoke() }) {
        LazyColumn(state = rememberLazyListState(), modifier = modifier) {
            items(data) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color =
                            if (selectedValue == it.value) selectedBackgroundColor
                            else Color.Neutral10
                        )
                        .padding(horizontal = space.x4, vertical = space.x3)
                        .clickable {
                            onSelect.invoke(it.value)
                            coroutineScope
                                .launch { bottomSheetState.hide() }
                                .invokeOnCompletion {
                                    if (!bottomSheetState.isVisible) {
                                        onDismiss.invoke()
                                    }
                                }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextBodyMedium(
                        text = it.name,
                        fontWeight = FontWeight.W500,
                        color = if (selectedValue == it.value) selectedTextColor else Color.Neutral90
                    )
                    if (it.description?.isNotBlank() == true) {
                        Spacer(modifier = Modifier.width(space.x1))
                        TextBodyMediumRegular(text = it.description, color = Color.Neutral60)
                    }
                }
            }
        }
    }
}