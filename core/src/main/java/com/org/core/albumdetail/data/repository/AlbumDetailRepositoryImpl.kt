package com.org.core.albumdetail.data.repository

import com.org.core.albumdetail.data.datasource.AlbumDetailDataSource
import com.org.core.albumdetail.data.model.DeezerAlbumDetailDataModel
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
}