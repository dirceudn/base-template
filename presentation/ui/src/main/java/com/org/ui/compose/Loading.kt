package com.org.ui.compose

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.org.ui.theme.boulder

@Composable
fun BallPulseSyncIndicator(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val animationValues = (1..3).map { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 12f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 300,
                    delayMillis = 70 * index,
                ),
                repeatMode = RepeatMode.Reverse,
            )
        )
    }
    Row {
        animationValues.forEach { animatedValue ->
            Ball(
                modifier = modifier
                    .padding(horizontal = 2.dp)
                    .offset(y = animatedValue.value.dp),
            )
        }
    }
}

@Composable
fun Ball(modifier: Modifier) {
    androidx.compose.foundation.Canvas(modifier = modifier, onDraw = {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = boulder,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = size.minDimension / 4
        )
    })

}