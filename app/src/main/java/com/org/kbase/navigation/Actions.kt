package com.org.kbase.navigation

import android.content.Context
import androidx.navigation.NavController
import com.org.route.OnAlbumDetailRouteContract

class NavigationActions(
    navController: NavController,
    onAlbumDetailRouteContract: OnAlbumDetailRouteContract,
    context: Context
) {
    val navigateToAlbumDetail: (id: Long) -> Unit = { id ->
        onAlbumDetailRouteContract.dataArgSetup(albumId = id)
        onAlbumDetailRouteContract.presenter(fromContext = context)
    }

    val onBackPress: () -> Unit = {
        navController.popBackStack()
    }

    val navigationUp: () -> Unit = {
        navController.navigateUp()
    }
}