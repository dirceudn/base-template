package com.org.core.home.data.repository

import com.org.core.common.State
import com.org.core.home.data.datasource.DeezerAlbumDataSource
import com.org.core.home.data.model.DeezerAlbumDataModel
import com.org.core.home.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AlbumRepositoryImpl(private val albumDataSource: DeezerAlbumDataSource) : AlbumRepository {

    private val _deezerAlbumMutableState = MutableStateFlow<State<DeezerAlbumDataModel>>(
        State.Uninitialized()
    )

    override val deezerAlbumState: Flow<State<DeezerAlbumDataModel>> = _deezerAlbumMutableState

    override suspend fun getAlbums() {
        if (_deezerAlbumMutableState.value !is State.Loading) {
            _deezerAlbumMutableState.value = State.Loading()
            _deezerAlbumMutableState.value = albumDataSource.getAlbums().fold({ errorDataModel ->
                State.Failure(error = errorDataModel)
            }, { deezerAlbumData ->
                State.Success(data = deezerAlbumData)
            })
        }
    }
}