package com.org.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.core.common.State
import com.org.core.home.domain.usecase.GetAlbumFlowUseCase
import com.org.core.home.domain.usecase.GetAlbumsUseCase
import com.org.home.model.AlbumCollectionState
import com.org.home.model.mapAlbumCollectionUiModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val albumsUseCase: GetAlbumsUseCase,
    private val albumFlowUseCase: GetAlbumFlowUseCase
) : ViewModel() {


    private val _mutableAlbumUiStateFLow = MutableStateFlow<AlbumCollectionState>(
        AlbumCollectionState.AlbumCollectionEmpty
    )
    val albumUiStateFLow: StateFlow<AlbumCollectionState> = _mutableAlbumUiStateFLow

    init {
        viewModelScope.launch {
            albumFlowUseCase.invoke()
        }
        viewModelScope.launch {
            albumsUseCase.invoke().collect { state ->
                when (state) {
                    is State.Uninitialized -> {
                        _mutableAlbumUiStateFLow.value = AlbumCollectionState.AlbumCollectionEmpty
                    }
                    is State.Loading -> {
                        _mutableAlbumUiStateFLow.value = AlbumCollectionState.AlbumCollectionLoading
                    }
                    is State.Failure -> {
                        _mutableAlbumUiStateFLow.value =
                            AlbumCollectionState.AlbumCollectionLoadError
                    }
                    is State.Success -> {
                        _mutableAlbumUiStateFLow.value =
                            state()?.mapAlbumCollectionUiModel()!!
                        Napier.d("MUTABLE STATE ${ _mutableAlbumUiStateFLow.value} ")
                    }
                }
            }
        }
    }
}