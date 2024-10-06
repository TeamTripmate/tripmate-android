package com.tripmate.android.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.ImageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
fun NetworkImage(
    imgUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(id = R.drawable.img_sample_character),
    failureImage: Painter = painterResource(id = R.drawable.img_sample_character),
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current

    CoilImage(
        imageRequest = {
            ImageRequest.Builder(context)
                .data(imgUrl)
                .crossfade(1000)
                .build() },
        imageLoader = {
            ImageLoader.Builder(context)
                .memoryCache { MemoryCache.Builder(context).maxSizePercent(0.25).build() }
                .crossfade(1000)
                .build() },
        modifier = modifier,
        component = rememberImageComponent {
            +PlaceholderPlugin.Loading(placeholder)
            +PlaceholderPlugin.Failure(placeholder)
        },
        imageOptions = ImageOptions(
            contentScale = contentScale,
            alignment = Alignment.Center,
            contentDescription = contentDescription,
        ),
        failure = {
            Image(
                painter = failureImage,
                contentDescription = "Failure Image",
                modifier = modifier,
            )
        },
    )
}

@Composable
fun ProfileImage(
    imgUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(id = R.drawable.img_profile_placeholder),
    failureImage: Painter = painterResource(id = R.drawable.img_profile_placeholder),
    contentScale: ContentScale = ContentScale.Crop,
) {
    NetworkImage(
        imgUrl = imgUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        placeholder = placeholder,
        failureImage = failureImage,
        contentScale = contentScale,
    )
}

@Composable
fun TripItemImage(
    imgUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(id = R.drawable.img_trip_placeholder),
    failureImage: Painter = painterResource(id = R.drawable.img_trip_placeholder),
    contentScale: ContentScale = ContentScale.Crop,
) {
    NetworkImage(
        imgUrl = imgUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        placeholder = placeholder,
        failureImage = failureImage,
        contentScale = contentScale,
    )
}

@ComponentPreview
@Composable
fun NetworkImagePreview() {
    TripmateTheme {
        NetworkImage(
            imgUrl = "",
            contentDescription = "",
        )
    }
}

@ComponentPreview
@Composable
fun ProfileImagePreview() {
    TripmateTheme {
        ProfileImage(
            imgUrl = "",
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.img_profile_placeholder),
            failureImage = painterResource(id = R.drawable.img_profile_placeholder),
        )
    }
}

@ComponentPreview
@Composable
fun TripItemImagePreview() {
    TripmateTheme {
        TripItemImage(
            imgUrl = "",
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.img_profile_placeholder),
            failureImage = painterResource(id = R.drawable.img_trip_placeholder),
        )
    }
}
