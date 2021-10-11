package com.org.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.org.ui.R
import com.org.ui.theme.boulder
import com.org.ui.theme.warmFlameEnd
import com.org.ui.theme.white

@Composable
fun PlayerIconView(
    modifier: Modifier = Modifier,
    borderColor: Color = boulder,
    progress: Float,
    size: Dp = 48.dp,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.then(modifier),
    ) {

        Box(
            modifier = Modifier
                .size(size)
                .background(color = borderColor, shape = CircleShape),
            contentAlignment = Alignment.Center,

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = white, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier.size(size / 2.6f),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_play),
                    colorFilter = ColorFilter.tint(
                        warmFlameEnd
                    ),
                    contentDescription = "audio guide content status",
                )

            }
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = progress,
                color = warmFlameEnd,
            )
        }
    }
}