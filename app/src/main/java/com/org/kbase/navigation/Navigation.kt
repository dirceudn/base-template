package com.org.kbase.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.org.home.screen.HomeScreen
import com.org.home.viewmodel.HomeViewModel
import com.org.route.OnAlbumDetailRouteContract
import io.github.aakira.napier.Napier

@Composable
fun NavigationAppRoute(
    homeViewModel: HomeViewModel,
    context: Context,
    onAlbumDetailRouteContract: OnAlbumDetailRouteContract
) {
    val controller = rememberNavController()
    val navActionController = remember(controller) {
        NavigationActions(
            navController = controller,
            onAlbumDetailRouteContract = onAlbumDetailRouteContract,
            context = context
        )
    }

    NavHost(navController = controller, startDestination = "home") {
        composable("home") {
            HomeScreen(homeViewModel = homeViewModel, onAlbumSelected = { albumId ->
                navActionController.navigateToAlbumDetail(albumId)
            })
        }
    }

}