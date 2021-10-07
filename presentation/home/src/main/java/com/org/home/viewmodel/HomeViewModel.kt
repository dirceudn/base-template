package com.org.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.core.common.State
import com.org.core.home.domain.usecase.GetAlbumFlowUseCase
import com.org.core.home.domain.usecase.GetAlbumsUseCase
import com.org.home.model.AlbumUIModel
import com.org.home.model.mapAlbumCollectionUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val albumsUseCase: GetAlbumsUseCase,
    private val albumFlowUseCase: GetAlbumFlowUseCase
) : ViewModel() {


    private val _mutableAlbumUiStateFLow = MutableStateFlow<List<AlbumUIModel>>(emptyList())
    val albumUiStateFLow: StateFlow<List<AlbumUIModel>> = _mutableAlbumUiStateFLow

    init {
        viewModelScope.launch {
            albumFlowUseCase.invoke()
        }
        viewModelScope.launch {
            albumsUseCase.invoke().collect { state ->
                when (state) {
                    is State.Uninitialized -> {
                    }
                    is State.Loading -> {
                    }
                    is State.Failure -> {
                    }
                    is State.Success -> {
                        _mutableAlbumUiStateFLow.value =
                            state()?.mapAlbumCollectionUiModel()?.albums ?: emptyList()
                    }
                }
            }
        }
    }
}