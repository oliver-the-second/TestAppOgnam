package com.ilhomsoliev.testappognam.shared.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import com.ilhomsoliev.testappognam.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun ImageBox(
    imageHttp: String,
    uri: Uri? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    if (uri == null) {
        GlideImage(
            modifier = modifier,
            imageModel = { imageHttp },
            imageOptions = ImageOptions(contentScale = contentScale),
            failure = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_bot_image),
                        contentDescription = null,
                    )
                }
            },
            component = rememberImageComponent {
                +ShimmerPlugin(
                    baseColor = MaterialTheme.colors.primary,
                    highlightColor = Color.Gray
                )
            },
        )
    } else {
        Image(
            painter = rememberImagePainter(
                data = uri
            ),
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale
        )
    }

}