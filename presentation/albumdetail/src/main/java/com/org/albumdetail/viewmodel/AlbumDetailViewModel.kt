package com.org.albumdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.albumdetail.model.AlbumDetailUiState
import com.org.albumdetail.model.TrackAlbumUiState
import com.org.albumdetail.model.toAlbumDetailUiModel
import com.org.albumdetail.model.toTrackAlbumDataUiModel
import com.org.core.albumdetail.domain.usecase.AlbumDetailFlowUseCase
import com.org.core.albumdetail.domain.usecase.GetAlbumDetailUseCase
import com.org.core.albumdetail.domain.usecase.GetAlbumTrackUseCase
import com.org.core.albumdetail.domain.usecase.TrackAlbumFlowUseCase
import com.org.core.common.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlbumDetailViewModel(
    private val albumDetailUseCase: GetAlbumDetailUseCase,
    private val trackAlbumFlowUseCase: TrackAlbumFlowUseCase,
    private val getAlbumTrackUseCase: GetAlbumTrackUseCase,
    private val albumDetailFlowUseCase: AlbumDetailFlowUseCase
) : ViewModel() {


    private val _mutableAlbumUiStateFLow =
        MutableStateFlow<AlbumDetailUiState?>(AlbumDetailUiState.AlbumEmpty)
    val albumDetailUiStateFLow: StateFlow<AlbumDetailUiState?> = _mutableAlbumUiStateFLow

    private val _mutableTrackUiState =
        MutableStateFlow<TrackAlbumUiState>(TrackAlbumUiState.TracksEmpty)
    val albumTrackUiStateFlow: StateFlow<TrackAlbumUiState> = _mutableTrackUiState

    private val _currentTrackAlbumSelected =
        MutableStateFlow<TrackAlbumUiState>(TrackAlbumUiState.TracksEmpty)
    val trackSelectedState: StateFlow<TrackAlbumUiState> = _currentTrackAlbumSelected


    init {
        viewModelScope.launch {
            albumDetailUseCase.invoke().collect { albumDetailState ->
                when (albumDetailState) {
                    is State.Uninitialized -> {
                        _mutableAlbumUiStateFLow.value = AlbumDetailUiState.AlbumEmpty
                    }
                    is State.Loading -> {
                        _mutableAlbumUiStateFLow.value = AlbumDetailUiState.AlbumLoading

                    }
                    is State.Failure -> {
                        _mutableAlbumUiStateFLow.value = AlbumDetailUiState.AlbumNotFound
                    }
                    is State.Success -> {
                        _mutableAlbumUiStateFLow.value =
                            albumDetailState()?.toAlbumDetailUiModel()
                    }
                }
            }
        }

        viewModelScope.launch {
            getAlbumTrackUseCase.invoke().collect { trackState ->
                when (trackState) {
                    is State.Uninitialized -> {
                        _mutableTrackUiState.value = TrackAlbumUiState.TracksEmpty
                    }
                    is State.Loading -> {
                        _mutableTrackUiState.value = TrackAlbumUiState.TracksLoading
                    }
                    is State.Failure -> {
                        _mutableTrackUiState.value = TrackAlbumUiState.NoTracksFound
                    }
                    is State.Success -> {
                        _mutableTrackUiState.value =
                            trackState()?.toTrackAlbumDataUiModel()
                                ?: TrackAlbumUiState.NoTracksFound
                    }
                }
            }

        }
    }

    fun getAlbum(id: Long) {
        viewModelScope.launch {
            albumDetailFlowUseCase.invoke(id = id)
        }
        viewModelScope.launch {
            trackAlbumFlowUseCase.invoke(id = id)
        }
    }

    fun selectTrack(id: Long?) {
        if (_mutableTrackUiState.value is TrackAlbumUiState.ListTracksUiModel) {
            val track =
                (albumTrackUiStateFlow.value as TrackAlbumUiState.ListTracksUiModel).tracks?.firstOrNull { track -> track.id == id }
            _currentTrackAlbumSelected.value = track ?: TrackAlbumUiState.NoTracksFound
        }
    }
}