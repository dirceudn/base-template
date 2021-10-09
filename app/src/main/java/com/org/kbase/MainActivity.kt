package com.org.kbase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.org.home.viewmodel.HomeViewModel
import com.org.kbase.navigation.NavigationAppRoute
import com.org.kbase.ui.theme.KBaseTheme
import com.org.route.OnAlbumDetailRouteContract
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    private val onAlbumRoute: OnAlbumDetailRouteContract by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KBaseTheme {
                val context = LocalContext.current
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavigationAppRoute(
                        homeViewModel = homeViewModel,
                        context = context,
                        onAlbumDetailRouteContract = onAlbumRoute
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KBaseTheme {
        //  Greeting("Android", homeViewModel)
    }
}