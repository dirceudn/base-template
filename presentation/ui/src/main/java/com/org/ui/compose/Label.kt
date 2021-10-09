package com.org.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.org.ui.theme.white


@Composable
fun AlbumDetailLabel(
    modifier: Modifier,
    title: String = "",
    artistName: String = "",
    releaseDate: String = ""
) {

    ConstraintLayout(modifier = modifier) {
        val (titleRef, artistNameRef, releaseDateRef) = createRefs()
        Box(modifier = Modifier.constrainAs(releaseDateRef) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)

        }) {
            Text(
                text = releaseDate,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = white,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            )

        }
        Box(modifier = Modifier.constrainAs(titleRef) {
            top.linkTo(releaseDateRef.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)

        }) {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = white,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            )

        }
        Box(modifier = Modifier
            .constrainAs(artistNameRef) {
                top.linkTo(titleRef.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text(
                text = artistName,
                style = MaterialTheme.typography.subtitle1.copy(color = white, fontSize = 18.sp)
            )

        }
    }

}