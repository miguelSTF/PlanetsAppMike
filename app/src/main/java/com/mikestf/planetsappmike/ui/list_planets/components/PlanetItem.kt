package com.mikestf.planetsappmike.ui.list_planets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.mikestf.planetsappmike.R
import com.mikestf.planetsappmike.domain.model.Planets
import com.mikestf.planetsappmike.utils.NUMBERS

@Composable
fun PlanetItem (
    modifier: Modifier = Modifier,
    item: Planets,
    onItemClicked: (String) -> Unit
) {
    Row(
    modifier = modifier
    .clickable { onItemClicked(item.url.filter { it in NUMBERS }) }
    .padding(start = 8.dp, top = 8.dp, bottom = 5.dp, end = 8.dp)
    ) {
        ImageContainer(modifier = Modifier.size(90.dp).background(Color.White)) {
            PlanetImage()
        }
        Spacer(Modifier.width(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(R.string.rotation_period) + " " +item.rotationPeriod,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(Modifier.padding(top = 10.dp))
    }
}

@Composable
private fun PlanetImage() {
    Box {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(getListMainImages().random())
                .size(Size.ORIGINAL)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun ImageContainer(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), RoundedCornerShape(4.dp)) {
        content()
    }
}

private fun getListMainImages(): List<String> {
    return listOf(
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Trantor-Coruscant.jpg/280px-Trantor-Coruscant.jpg",
        "https://static.wikia.nocookie.net/esstarwars/images/d/dc/Concordia.png/revision/latest?cb=20180519184307",
        "https://static.wikia.nocookie.net/esstarwars/images/1/16/Coruscant-EotE.jpg/revision/latest?cb=20221030195452",
        "https://e0.pxfuel.com/wallpapers/632/671/desktop-wallpaper-star-wars-planets-star-wars-panoramic.jpg"
    )
}