package com.posite.composeexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isFavorite by rememberSaveable {
                mutableStateOf(false)
            }
            ImageCard(
                isFavorite = isFavorite,
            ) { favorite ->
                isFavorite = favorite
            }
        }
    }
}

@Composable
fun ImageCard(
    isFavorite: Boolean,
    onClickFavorite: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = "서울의 봄",
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier.matchParentSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = {
                    onClickFavorite(isFavorite.not())
                }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}