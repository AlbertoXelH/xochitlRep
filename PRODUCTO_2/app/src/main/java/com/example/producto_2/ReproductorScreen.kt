package com.example.producto_2

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text

data class Song(
    val title: String,
    val artist: String,
    val resId: Int,
    val imageId: Int
)

val songs = listOf(
    Song("Can't get enough", "Blockheads", R.raw.cant_we_get_enogh, R.drawable.bockheads),
    Song("Rise", "Blockheads", R.raw.rise, R.drawable.bockheads)
)

@Composable
fun ReproductorScreen() {
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var currentSong by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current

    fun playOrPauseSong(resId: Int) {
        if (mediaPlayer == null || currentSong != resId) {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, resId).apply { start() }
            currentSong = resId
        } else {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            } else {
                mediaPlayer?.start()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Mis Canciones",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(36.dp))
            }
            items(songs.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { playOrPauseSong(songs[index].resId) }
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = songs[index].imageId),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = songs[index].title,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Text(
                            text = songs[index].artist,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


