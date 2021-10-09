package com.org.albumdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.albumdetail.model.AlbumDetailUiState
import com.org.albumdetail.model.toAlbumDetailUiModel
import com.org.core.albumdetail.domain.usecase.AlbumDetailFlowUseCase
import com.org.core.albumdetail.domain.usecase.GetAlbumDetailUseCase
import com.org.core.common.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlbumDetailViewModel(
    private val albumDetailUseCase: GetAlbumDetailUseCase,
    private val albumDetailFlowUseCase: AlbumDetailFlowUseCase
) : ViewModel() {


    private val _mutableAlbumUiStateFLow =
        MutableStateFlow<AlbumDetailUiState?>(AlbumDetailUiState.AlbumEmpty)
    val albumDetailUiStateFLow: StateFlow<AlbumDetailUiState?> = _mutableAlbumUiStateFLow


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
    }

    fun getAlbum(id: Long) {
        viewModelScope.launch {
            albumDetailFlowUseCase.invoke(id = id)
        }
    }
}