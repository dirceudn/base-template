package com.org.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.request.CachePolicy

@ExperimentalAnimationApi
@Composable
fun AlbumCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    artist: String = "",
    albumTitle: String = "",
    cover: String? = "",
    @DrawableRes placeHolder: Int,
    onClick: () -> Unit
) {

    val padding = 10.dp
    Column(
        Modifier
            .clickable(onClick = onClick)
            .padding(padding)
            .fillMaxWidth()
    ) {

        AlbumCardCover(
            modifier = modifier,
            placeHolder = placeHolder,
            cover = cover,
            elevation = elevation
        ) {
            onClick()
        }
        Spacer(Modifier.size(padding))
        AlbumTitleRow(title = albumTitle, artist = artist)

    }
}

@Composable
fun AlbumTitleRow(title: String, artist: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.LightGray,
                    fontWeight = FontWeight.W700,
                    fontSize = 12.sp
                )
            )
            Text(
                text = artist,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp
                )
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AlbumCardCover(
    modifier: Modifier,
    cover: String? = "",
    elevation: Dp = 0.dp,
    @DrawableRes placeHolder: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() },
        elevation = elevation
    ) {
        Image(
            painter = rememberImagePainter(
                data = cover,
                builder = {
                    fadeIn(initialAlpha = 0.4f)
                    placeholder(placeHolder)
                    error(placeHolder)
                    memoryCachePolicy(CachePolicy.ENABLED)
                }
            ),
            contentDescription = "Album cover",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    }
}