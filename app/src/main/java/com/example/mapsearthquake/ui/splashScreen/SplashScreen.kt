package com.example.mapsearthquake.ui.splashScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mapsearthquake.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mapsearthquake.theme.Gray


@ExperimentalAnimationApi
@Composable
fun SplashScreen(navController: NavHostController) {
    var goToNextScreen by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        delay(5000)
        goToNextScreen = true
    }

    if (goToNextScreen) {
        navController.navigate("earthquake_screen")
    } else {
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize(),
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedPreloader(modifier = Modifier.size(150.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Deprem Bilgi Sistemi",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Gray,
                        lineHeight = 72.sp,
                        textAlign = TextAlign.Center,
                        fontSize = 44.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.loading
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}





