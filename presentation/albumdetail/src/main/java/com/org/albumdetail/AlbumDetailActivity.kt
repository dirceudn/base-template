package com.org.albumdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.org.albumdetail.model.AlbumDetailUiState
import com.org.albumdetail.model.GenreUiModel
import com.org.albumdetail.viewmodel.AlbumDetailViewModel
import com.org.ui.compose.AlbumCardCover
import com.org.ui.compose.AlbumDetailLabel
import com.org.ui.compose.BackAppBar
import com.org.ui.compose.BallPulseSyncIndicator
import com.org.ui.theme.PurpleTheme
import com.org.ui.theme.boulder
import com.org.ui.theme.warmFlameEnd
import com.org.ui.theme.warmFlameStart
import org.koin.androidx.viewmodel.ext.android.viewModel


class AlbumDetailActivity : AppCompatActivity() {

    private val albumId by lazy { extractAlbumId(intent) }

    private val albumDetailViewModel: AlbumDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PurpleTheme {
                albumDetailViewModel.getAlbum(albumId)
                val context = LocalContext.current
                Surface(color = MaterialTheme.colors.background) {
                    AlbumPageScreen(albumDetailViewModel = albumDetailViewModel, context = context)
                }
            }
        }
    }

    companion object {
        fun extractAlbumId(intent: Intent): Long {
            val bundle = intent.extras?.get("arg") as Bundle
            return bundle.getLong("data")
        }
    }
}

@Composable
fun AlbumPageScreen(albumDetailViewModel: AlbumDetailViewModel, context: Context) {
    Box(
        Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        warmFlameStart,
                        warmFlameEnd
                    )
                )
            )
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            AlbumDetailActionBar(context = context)
            AlbumDetailPage(albumDetailViewModel = albumDetailViewModel)
        }
    }

}

@Composable
fun AlbumDetailActionBar(context: Context) {
    BackAppBar(
        onBackClicked = { (context as? AlbumDetailActivity)?.onBackPressed() },
        backIcon = R.drawable.ic_baseline_arrow_back_ios_24,
        title = context.getString(R.string.title_album)
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlbumDetailPage(albumDetailViewModel: AlbumDetailViewModel) {
    val albumStateObject = albumDetailViewModel.albumDetailUiStateFLow.collectAsState()
    when (albumStateObject.value) {
        is AlbumDetailUiState.AlbumNotFound -> {
            NoAlbumView()
        }
        is AlbumDetailUiState.AlbumDetailUiModel -> {
            BottomSheetScaffold(sheetContent = {

            }) {
                AlbumDetailView(album = (albumStateObject.value as AlbumDetailUiState.AlbumDetailUiModel))
            }
        }
        is AlbumDetailUiState.AlbumLoading, AlbumDetailUiState.AlbumEmpty -> {
            LoadingAlbumView()
        }
        else -> {
            ErrorAlbumView()
        }
    }
}

@Composable
fun NoAlbumView() {

}

@Composable
fun AlbumDetailView(album: AlbumDetailUiState.AlbumDetailUiModel) {

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(2f, fill = false)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AlbumCardCover(
                modifier = Modifier
                    .size(200.dp),
                elevation = 20.dp,
                placeHolder = R.drawable.music_album,
                cover = album.coverXl
            ) {

            }
            Spacer(modifier = Modifier.height(20.dp))
            AlbumDetailLabel(
                modifier = Modifier.fillMaxWidth(),
                title = album.title ?: "--",
                artistName = album.label ?: "--",
                releaseDate = album.releaseDate ?: "--"
            )

        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .padding(start = 30.dp)
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {

            GenreLabel(label = "Genre", fontSize = 20.sp)
            GenreList(genres = album.genresUiModels)
        }
    }

}

@Composable
fun GenreLabel(label: String?, fontSize: TextUnit) {
    Text(
        text = label ?: "--",
        style = MaterialTheme.typography.subtitle1.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.W700,
            color = boulder
        )
    )
}

@Composable
fun GenreList(genres: List<GenreUiModel>?) {
    if (!genres.isNullOrEmpty()) {
        LazyRow(modifier = Modifier.padding(end = 8.dp)) {
            items(genres) { item ->
                GenreItem(genre = item)

            }
        }
    }
}

@Composable
fun GenreItem(genre: GenreUiModel) {
    Box(modifier = Modifier.padding(top = 20.dp, end = 10.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AlbumCardCover(
                modifier = Modifier.size(60.dp),
                elevation = 8.dp,
                placeHolder = R.drawable.music_album,
                cover = genre.picture
            ) {

            }
            GenreLabel(label = genre.name, fontSize = 12.sp)
        }

    }
}

@Composable
fun LoadingAlbumView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BallPulseSyncIndicator(modifier = Modifier.size(40.dp))
    }
}

@Composable
fun ErrorAlbumView() {

}