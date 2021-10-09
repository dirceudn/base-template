package com.org.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsHeight
import com.org.ui.theme.transparent
import com.org.ui.theme.white

@Composable
fun BackAppBar(
    onBackClicked: () -> Unit,
    @DrawableRes backIcon: Int,
    title: String
) {
    Column(modifier = Modifier.clickable {
        onBackClicked()
    }) {
        Spacer(
            Modifier
                .background(transparent)
                .statusBarsHeight() // Match the height of the status bar
        )
        Spacer(
            Modifier
                .background(transparent)
                .height(10.dp)
        )

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (backIconRef, titleActionRef, moreSettingRef) = createRefs()
            Box(modifier = Modifier
                .clickable {
                    onBackClicked()
                }
                .constrainAs(backIconRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }) {
                BackIcon(backIcon = backIcon)
            }
            Box(modifier = Modifier.constrainAs(titleActionRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)

            }) {
                TitleActionBar(title = title)
            }
            Box(modifier = Modifier.constrainAs(moreSettingRef) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
                MoreSettings(iconSettings = backIcon)
            }

        }
    }
}

@Composable
fun BackIcon(@DrawableRes backIcon: Int) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .background(transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            Modifier
                .background(transparent)
                .width(16.dp)
        )
        Image(
            imageVector = ImageVector.vectorResource(id = backIcon),
            colorFilter = ColorFilter.tint(white),
            contentDescription = "Deezer App Bar Icon",
        )
        Spacer(
            Modifier
                .background(transparent)
                .width(8.dp)
        )
    }
}

@Composable
fun TitleActionBar(title: String) {
    Text(text = title, style = MaterialTheme.typography.subtitle1.copy(color = white))
}

@Composable
fun MoreSettings(@DrawableRes iconSettings: Int) {

}