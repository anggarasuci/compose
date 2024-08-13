package com.anggara.compose_lib.ui.expandable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anggara.compose_lib.theme.Neutral10
import com.anggara.compose_lib.theme.Neutral90
import com.anggara.compose_lib.theme.space
import com.anggara.compose_lib.utils.clickableWithRipple

@Composable
fun ExpandableView(
    modifier: Modifier = Modifier,
    headContainerModifier: Modifier = Modifier,
    bodyContainerModifier: Modifier = Modifier,
    expandableHoverColor: Color = Color.Transparent,
    backgroundColor: Color = Color.Neutral10,
    expandBackgroundColor: Color = Color.Neutral10,
    withArrow: Boolean = true,
    arrowSize: Dp = space.x8,
    arrowSpaceEnd: Dp = space.x2,
    arrowAlignment: Alignment = Alignment.TopEnd,
    arrowColor: Color = Color.Neutral90,
    isWholeContentClickableExpandable: Boolean = false,
    onExpanded: (isExpanded: Boolean) -> Unit = {},
    headContent: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val density = LocalDensity.current
    val rotationAngle by animateFloatAsState(
        targetValue = if (!isExpanded) 450F else 270F,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = ""
    )
    val clickableModifier = Modifier
        .clickableWithRipple(color = expandableHoverColor) {
            isExpanded = !isExpanded
            onExpanded.invoke(isExpanded)
        }

    Column(
        modifier = Modifier
            .then(if (isWholeContentClickableExpandable) clickableModifier else Modifier)
            .background(color = if (isExpanded) expandBackgroundColor else backgroundColor)
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(if (!isWholeContentClickableExpandable) clickableModifier else Modifier)
                .then(headContainerModifier),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                headContent()
            }
            if (withArrow) {
                Row(modifier = Modifier.align(arrowAlignment)) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "expandable arrow",
                        tint = arrowColor,
                        modifier = Modifier
                            .size(arrowSize)
                            .rotate(rotationAngle),
                    )
                    Spacer(modifier = Modifier.width(arrowSpaceEnd))
                }
            }
        }
        Spacer(modifier = Modifier.height(space.x2))
        AnimatedVisibility(visible = isExpanded,
            enter = slideInVertically {
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()) {

            Column(modifier = bodyContainerModifier) {
                bodyContent()
            }
        }
    }
}