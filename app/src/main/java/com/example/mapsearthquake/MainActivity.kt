package com.example.mapsearthquake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mapsearthquake.theme.MapsEarthquakeTheme
import com.example.mapsearthquake.ui.earthquake.view.EarthquakeScreen
import com.example.mapsearthquake.ui.earthquakeMap.view.EarthquakeMapScreen
import com.example.mapsearthquake.ui.splashScreen.SplashScreen
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapsEarthquakeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    NavigationHost(navController = navController)
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("earthquake_screen") {
            EarthquakeScreen(navController = navController)
        }

        composable("earthquake_map_screen/{latitude}/{longitude}") { backStackEntry ->
            val latitude = backStackEntry.arguments?.getString("latitude")?.toDouble() ?: 0.0
            val longitude = backStackEntry.arguments?.getString("longitude")?.toDouble() ?: 0.0
            EarthquakeMapScreen(navController, LatLng(latitude, longitude))
        }
    }
}

