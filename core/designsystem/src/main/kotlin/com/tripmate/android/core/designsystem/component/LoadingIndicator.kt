package com.tripmate.android.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
fun LoadingIndicator(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isLoading,
        modifier = modifier,
    ) {
        Box(modifier = Modifier.clickable { }) {
            LoadingAnimation(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    assetName: String = "loading_animation",
    iterations: Int = LottieConstants.IterateForever,
    animationSpeed: Float = 1f,
    animationEndProgress: Float = 1f,
    lottieColor: Color = Primary01,
    onAnimationEnd: () -> Unit = {},
) {
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR_FILTER,
            value = SimpleColorFilter(lottieColor.toArgb()),
            "**",
        ),
    )
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.Asset(assetName.plus(".json")))
    val progress by animateLottieCompositionAsState(lottieComposition)

    Box(modifier = modifier.fillMaxSize()) {
        if (assetName.isNotEmpty()) {
            LottieAnimation(
                modifier = modifier.align(Alignment.Center),
                composition = lottieComposition,
                iterations = iterations,
                speed = animationSpeed,
                dynamicProperties = dynamicProperties,
            )
        }
    }

    if (progress >= animationEndProgress && iterations != LottieConstants.IterateForever) {
        onAnimationEnd()
    }
}

@ComponentPreview
@Composable
fun LoadingWheelPreview() {
    TripmateTheme {
        LoadingIndicator(
            isLoading = true,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
