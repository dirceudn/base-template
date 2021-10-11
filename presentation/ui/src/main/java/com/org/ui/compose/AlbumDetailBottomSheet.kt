package com.org.ui.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.org.ui.theme.white


@Composable
@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
fun DeezerAlbumBottomSheet(
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    topBar: (@Composable () -> Unit)? = null,
    contentExpanded: @Composable () -> Unit,
    radius: Dp,
    onStateClick: () -> Unit,
    contentCollapsed: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit,
) {
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = radius, topEnd = radius),
        topBar = topBar,
        sheetContent = {
            ModalContent {
                ContentExpanded {
                    contentExpanded()
                }
                ContentCollapsed(
                    isCollapsed = scaffoldState.bottomSheetState.isCollapsed,
                    onSheetClick = onStateClick
                ) {
                    contentCollapsed()
                }
            }
        },
        sheetPeekHeight = 72.dp
    ) {
        bodyContent()
    }

}

@Composable
fun ModalContent(
    heightFraction: Float = 0.8f,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = heightFraction)
    ) {
        content()
    }
}

@Composable
fun ContentExpanded(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        content()
    }
}

@Composable
fun ContentCollapsed(
    isCollapsed: Boolean,
    onSheetClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(white)
            .clickable(
                onClick = { if (isCollapsed) onSheetClick() },
                enabled = isCollapsed
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
