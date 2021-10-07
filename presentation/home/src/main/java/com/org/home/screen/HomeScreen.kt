package com.org.home.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.org.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val state = homeViewModel.albumUiStateFLow.collectAsState()
    Text(text = "Hello ${state.value.size}!")
}