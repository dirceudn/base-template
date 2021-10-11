package com.org.home.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.org.home.R
import com.org.home.model.AlbumCollectionState
import com.org.home.viewmodel.HomeViewModel
import com.org.ui.compose.AlbumCard
import com.org.ui.compose.LoadingView

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, onAlbumSelected: (Long) -> Unit) {
    val state = homeViewModel.albumUiStateFLow.collectAsState()
    AlbumGrid(collectionState = state) { albumId ->
        onAlbumSelected(albumId)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumGrid(collectionState: State<AlbumCollectionState>, itemSelected: (Long) -> Unit) {
    when (collectionState.value) {
        is AlbumCollectionState.AlbumsCollection -> {
            val albums =
                (collectionState.value as? AlbumCollectionState.AlbumsCollection)?.albums
            GridAlbum(albums = albums) {
                itemSelected(it)
            }
        }
        is AlbumCollectionState.AlbumCollectionLoading -> {
            LoadingView()
        }
        else -> {
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridAlbum(albums: List<AlbumCollectionState.AlbumUIModel>?, itemSelected: (Long) -> Unit) {
    LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
        if (!albums.isNullOrEmpty()) {
            items(albums) { album ->
                AlbumItem(item = album) { albumId ->
                    itemSelected(albumId)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AlbumItem(item: AlbumCollectionState.AlbumUIModel, onAlbumItemSelected: (Long) -> Unit) {
    AlbumCard(
        elevation = 10.dp,
        albumTitle = item.title ?: "-???-",
        artist = item.artistName ?: "-???-",
        cover = item.coverBig,
        modifier = Modifier.size(200.dp),
        placeHolder = R.drawable.music_album
    ) {
        item.id?.let { id ->
            onAlbumItemSelected(id)
        }
    }
}