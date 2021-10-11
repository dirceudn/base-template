package com.org.core.albumdetail.data.repository

import com.org.core.albumdetail.data.datasource.AlbumDetailDataSource
import com.org.core.albumdetail.data.model.DeezerAlbumDetailDataModel
import com.org.core.albumdetail.data.model.DeezerAlbumTracksDataModel
import com.org.core.albumdetail.domain.repository.AlbumDetailRepository
import com.org.core.common.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AlbumDetailRepositoryImpl(private val albumDetailDataSource: AlbumDetailDataSource) :
    AlbumDetailRepository {

    private val _deezerAlbumDetailMutableState =
        MutableStateFlow<State<DeezerAlbumDetailDataModel>>(
            State.Uninitialized()
        )

    private val _deezerAlbumTracksMutableState =
        MutableStateFlow<State<DeezerAlbumTracksDataModel>>(
            State.Uninitialized()
        )

    override suspend fun getAlbum(id: Long) {
        if (_deezerAlbumDetailMutableState.value !is State.Loading) {
            _deezerAlbumDetailMutableState.value = State.Loading()
            _deezerAlbumDetailMutableState.value =
                albumDetailDataSource.getAlbum(id = id).fold({ errorDataModel ->
                    State.Failure(error = errorDataModel)
                }, { deezerAlbumDetailData ->
                    State.Success(data = deezerAlbumDetailData)
                })
        }
    }

    override val deezerAlbumDetailState: Flow<State<DeezerAlbumDetailDataModel>>
        get() = _deezerAlbumDetailMutableState

    override val deezerAlbumTrackState: Flow<State<DeezerAlbumTracksDataModel>>
        get() = _deezerAlbumTracksMutableState

    override suspend fun getTracksFromAlbum(id: Long) {
        if (_deezerAlbumTracksMutableState.value !is State.Loading) {
            _deezerAlbumTracksMutableState.value = State.Loading()
            _deezerAlbumTracksMutableState.value =
                albumDetailDataSource.getTrackFromAlbum(id = id).fold({ errorDataModel ->
                    State.Failure(error = errorDataModel)
                }, { deezerAlbumTrackData ->
                    State.Success(data = deezerAlbumTrackData)
                })
        }
    }
}