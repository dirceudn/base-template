package com.org.albumdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.org.albumdetail.model.AlbumDetailUiState
import com.org.albumdetail.model.ContributorUiModel
import com.org.albumdetail.model.GenreUiModel
import com.org.albumdetail.model.TrackAlbumUiState
import com.org.albumdetail.viewmodel.AlbumDetailViewModel
import com.org.ui.compose.*
import com.org.ui.theme.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalAnimationApi
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

@ExperimentalAnimationApi
@OptIn(ExperimentalAnimationApi::class)
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

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalAnimationApi
@Composable
fun AlbumDetailActionBar(context: Context) {
    BackAppBar(
        onBackClicked = { (context as? AlbumDetailActivity)?.onBackPressed() },
        backIcon = R.drawable.ic_baseline_arrow_back_ios_24,
        title = context.getString(R.string.title_album)
    )
}


@ExperimentalAnimationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlbumDetailPage(albumDetailViewModel: AlbumDetailViewModel) {
    val albumStateObject = albumDetailViewModel.albumDetailUiStateFLow.collectAsState()
    val trackAlbumStateObject = albumDetailViewModel.albumTrackUiStateFlow.collectAsState()
    val selectTrackState = albumDetailViewModel.trackSelectedState.collectAsState()

    when (albumStateObject.value) {
        is AlbumDetailUiState.AlbumNotFound -> {
            NoAlbumView()
        }
        is AlbumDetailUiState.AlbumDetailUiModel -> {
            AlbumDetailView(
                album = (albumStateObject.value as AlbumDetailUiState.AlbumDetailUiModel),
                trackAlbumStateObject = trackAlbumStateObject,
                onTrackSelected = { trackId ->
                    albumDetailViewModel.selectTrack(id = trackId)
                },
                selectTrackState = selectTrackState
            )
        }
        is AlbumDetailUiState.AlbumLoading, AlbumDetailUiState.AlbumEmpty -> {
            LoadingView()
        }
        else -> {
            ErrorAlbumView()
        }
    }
}

@Composable
fun NoAlbumView() {
    val context = LocalContext.current
    NoDataFound(title = context.getString(R.string.not_album_found))
}

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlbumDetailView(
    album: AlbumDetailUiState.AlbumDetailUiModel,
    trackAlbumStateObject: State<TrackAlbumUiState>,
    onTrackSelected: (id: Long?) -> Unit,
    selectTrackState: State<TrackAlbumUiState>
) {

    val scaffoldBottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )
    val scope = rememberCoroutineScope()

    val onStateClick: () -> Unit = {
        scope.launch {
            if (scaffoldBottomSheetState.bottomSheetState.isCollapsed) {
                scaffoldBottomSheetState.bottomSheetState.expand()
            } else {
                scaffoldBottomSheetState.bottomSheetState.collapse()
            }
        }
    }
    val radius = (30 * scaffoldBottomSheetState.currentFraction).dp

    DeezerAlbumBottomSheet(
        modifier = Modifier.fillMaxSize(),
        contentExpanded = {
            AlbumTrackContent(trackAlbumStateObject = trackAlbumStateObject, album = album) { id ->
                onTrackSelected(id)
            }

        },
        radius = radius,
        onStateClick = onStateClick,
        contentCollapsed = {
            PlayerView(selectTrackState = selectTrackState)
        }) {

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            warmFlameStart,
                            warmFlameEnd
                        )
                    )
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .weight(1f, fill = false)
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

                    Label(label = "Genre", fontSize = 20.sp, color = white)
                    GenreList(genres = album.genresUiModels)
                    Spacer(modifier = Modifier.height(10.dp))
                    Label(label = "Contributors", fontSize = 20.sp, color = white)
                    Contributors(contributors = album.contributorsUiModel)
                }

            }
        }
    }
}

@Composable
fun PlayerView(selectTrackState: State<TrackAlbumUiState>) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_bottom_open),
            contentDescription = "drawer opener"
        )
        Column(
            modifier = Modifier
                .padding(start = 30.dp)
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                PlayerIconView(modifier = Modifier.size(40.dp), progress = 2f) {

                }
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    if (selectTrackState.value is TrackAlbumUiState.TracksUiModel) {
                        Label(
                            label = (selectTrackState.value as TrackAlbumUiState.TracksUiModel).title,
                            fontSize = 14.sp,
                            color = warmFlameEnd
                        )
                        Label(
                            label = (selectTrackState.value as TrackAlbumUiState.TracksUiModel).duration,
                            fontSize = 11.sp,
                            color = warmFlameEnd
                        )
                    }
                }
            }

        }
        Divider(modifier = Modifier.padding(horizontal = 16.dp), color = warmFlameStart)
    }
}

@Composable
fun AlbumTrackContent(
    trackAlbumStateObject: State<TrackAlbumUiState>,
    album: AlbumDetailUiState.AlbumDetailUiModel,
    onTrackClicked: (id: Long?) -> Unit,
) {
    when (trackAlbumStateObject.value) {
        is TrackAlbumUiState.TracksEmpty -> {
            TrackEmptyView()
        }
        is TrackAlbumUiState.TracksLoading -> {
            LoadingView()
        }
        is TrackAlbumUiState.NoTracksFound -> {
            TrackAlbumNoData()
        }
        is TrackAlbumUiState.ListTracksUiModel -> {
            TrackAlbumView(
                tracks = (trackAlbumStateObject.value as? TrackAlbumUiState.ListTracksUiModel)?.tracks,
                album = album
            ) { id ->
                onTrackClicked(id)
            }
        }
        else -> {
            TrackAlbumNoData()
        }
    }

}

@Composable
fun TrackAlbumNoData() {
    val context = LocalContext.current
    NoDataFound(title = context.getString(R.string.not_tracks_found))
}

@Composable
fun TrackAlbumView(
    tracks: List<TrackAlbumUiState.TracksUiModel>?,
    album: AlbumDetailUiState.AlbumDetailUiModel,
    onTrackClicked: (id: Long?) -> Unit,
) {
    if (!tracks.isNullOrEmpty()) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 20.dp)
                .background(transparent)
        ) {
            items(tracks) { track ->
                TrackAlbumItemRow(track, album) { id ->
                    onTrackClicked(id)
                }
            }
        }
    }
}

@Composable
fun TrackAlbumItemRow(
    track: TrackAlbumUiState.TracksUiModel,
    album: AlbumDetailUiState.AlbumDetailUiModel,
    onTrackClicked: (id: Long?) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTrackClicked(track.id) }
            .height(80.dp)
    ) {
        AlbumCardCover(
            modifier = Modifier.size(60.dp),
            elevation = 8.dp,
            placeHolder = R.drawable.music_album,
            cover = album.coverBig
        ) {
            onTrackClicked(track.id)
        }
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Label(label = track.title, fontSize = 16.sp, color = funGray)
            Label(label = track.duration, fontSize = 12.sp, color = funGray)
        }
    }
}

@Composable
fun TrackEmptyView() {
    val context = LocalContext.current
    NoDataFound(title = context.getString(R.string.not_tracks_found))
}

@Composable
fun Contributors(contributors: List<ContributorUiModel>?) {
    if (!contributors.isNullOrEmpty()) {
        LazyRow(modifier = Modifier.padding(end = 8.dp)) {
            items(contributors) { item ->
                CoverItem(label = item.name, picture = item.picture)
            }
        }
    }
}

@Composable
fun Label(label: String?, fontSize: TextUnit, color: Color) {
    Text(
        text = label ?: "--",
        style = MaterialTheme.typography.subtitle1.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.W700,
            color = color
        )
    )
}

@Composable
fun GenreList(genres: List<GenreUiModel>?) {
    if (!genres.isNullOrEmpty()) {
        LazyRow(modifier = Modifier.padding(end = 8.dp)) {
            items(genres) { item ->
                CoverItem(label = item.name, picture = item.picture)
            }
        }
    }
}

@Composable
fun CoverItem(label: String?, picture: String?) {
    Box(modifier = Modifier.padding(top = 20.dp, end = 10.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AlbumCardCover(
                modifier = Modifier.size(60.dp),
                elevation = 8.dp,
                placeHolder = R.drawable.music_album,
                cover = picture
            ) {

            }
            Label(label = label, fontSize = 12.sp, color = white)
        }

    }
}


@Composable
fun ErrorAlbumView() {
    val context = LocalContext.current
    NoDataFound(title = context.getString(R.string.not_album_found))
}