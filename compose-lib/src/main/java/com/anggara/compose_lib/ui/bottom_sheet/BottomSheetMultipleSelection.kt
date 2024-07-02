package com.anggara.compose_lib.ui.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Neutral40
import com.anggara.compose_lib.theme.Neutral50
import com.anggara.compose_lib.theme.Neutral60
import com.anggara.compose_lib.theme.Neutral90
import com.anggara.compose_lib.theme.PrimaryMain
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.ui.button.Button
import com.anggara.compose_lib.ui.text.TextBodyMedium
import com.anggara.compose_lib.ui.text.TextBodyMediumRegular
import com.anggara.compose_lib.utils.clickableWithRipple
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetMultipleSelection(
    isShown: Boolean,
    title: String,
    data: List<OptionModel>,
    modifier: Modifier = Modifier,
    selectedValues: List<String> = listOf(),
    cancelText: String = "Cancel",
    okText: String = "Ok",
    enableOk: Boolean = true,
    enableCancel: Boolean = true,
    okTextColor: Color = Color.Neutral10,
    cancelTextColor: Color = Color.PrimaryMain,
    okBackgroundColor: Color = Color.PrimaryMain,
    cancelBackgroundColor: Color = Color.Neutral10,
    cancelBorderColor: Color = Color.Neutral50,
    okBorderColor: Color = Color.PrimaryMain,
    onOk: (values: List<String>) -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var selectedValueState by remember {
        mutableStateOf(selectedValues)
    }

    fun onHideAfterAction() {
        coroutineScope
            .launch { bottomSheetState.hide() }
            .invokeOnCompletion {
                if (!bottomSheetState.isVisible) {
                    onDismiss.invoke()
                }
            }
    }

    fun onSelect(value: String, isChecked: Boolean) {
        selectedValueState = (
                if (isChecked) (selectedValueState + value)
                else (selectedValueState.filter { it != value }))
            .distinct()
    }

    BottomSheet(
        isShown = isShown,
        title = title,
        bottomSheetState = bottomSheetState,
        onDismiss = { onDismiss.invoke() }) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = modifier.padding(bottom = space.x6)
            ) {
                items(data) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickableWithRipple {
                                onSelect(it.value, !selectedValueState.contains(it.value))
                            }
                            .background(
                                Color.Neutral10
                            )
                            .padding(horizontal = space.x4, vertical = space.x3),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = if (selectedValueState.contains(it.value))
                                Icons.Default.CheckBox else Icons.Default.CheckBoxOutlineBlank,
                            contentDescription = "checkbox multi selection",
                            tint = if (selectedValueState.contains(it.value)) Color.PrimaryMain
                            else Color.Neutral40,
                            modifier = Modifier
                                .size(space.x8)
                                .padding(top = space.x1)
                        )
                        Spacer(modifier = Modifier.width(space.x1))
                        TextBodyMedium(
                            text = it.name,
                            fontWeight = FontWeight.W500,
                            color = Color.Neutral90
                        )
                        if (it.description?.isNotBlank() == true) {
                            Spacer(modifier = Modifier.width(space.x1))
                            TextBodyMediumRegular(text = it.description, color = Color.Neutral60)
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(space.x10)) }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = space.x4)
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    text = cancelText,
                    textColor = cancelTextColor,
                    borderColor = cancelBorderColor,
                    backgroundColor = cancelBackgroundColor,
                    isBorderShown = cancelBorderColor != cancelBackgroundColor,
                    enabled = enableCancel,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = space.x2),
                    onClick = {
                        selectedValueState = selectedValues
                        onCancel.invoke()
                        onHideAfterAction()
                    })

                Button(
                    text = okText,
                    textColor = okTextColor,
                    borderColor = okBorderColor,
                    backgroundColor = okBackgroundColor,
                    isBorderShown = okBorderColor != okBackgroundColor,
                    enabled = enableOk,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = space.x2),
                    onClick = {
                        onOk.invoke(selectedValueState)
                        onHideAfterAction()
                    })
            }
        }
    }
}