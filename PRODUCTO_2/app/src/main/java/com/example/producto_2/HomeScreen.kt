package com.example.producto_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.tv.material3.Text
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

val imagesRow1 = listOf(
    R.drawable.wattpad,
    R.drawable.spotify,
    R.drawable.twitch,
    R.drawable.plutotv,
    R.drawable.youtube,
    R.drawable.discovery_go
)

val imagesRow2 = listOf(
    R.drawable.netflix,
    R.drawable.disney,
    R.drawable.crunchiroll,
    R.drawable.paramount,
    R.drawable.hbo,
    R.drawable.starplus
)

val imagesRow3 = listOf(
    R.drawable.google_play,
    R.drawable.googletv,
    R.drawable.chromo,
    R.drawable.deportes,
    R.drawable.dolby,
    R.drawable.config
)


@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
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
        ) {
            WeatherInfo(navController)
        }
    }
}

data class WeatherResponse(
    val main: Main
)

data class Main(
    val temp: Float
)

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}

@Composable
fun WeatherInfo(navController: NavHostController) {
    val apiKey = "57934742576511ad730fbf0f3dcf7cf5"
    val city = "México"
    val weatherService = remember {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    var temperature by remember { mutableStateOf<Float?>(null) }
    val currentDateTime by remember { mutableStateOf(getCurrentDateTime()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = weatherService.getWeather(city, apiKey)
                temperature = response.main.temp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp).clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = currentDateTime,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 16.sp
        )
        if (temperature != null) {
            Text(
                text = "Temperatura Actual: ${temperature}°C",
                color = Color.White,
                fontSize = 18.sp
            )
        } else {
            Text(
                text = "Cargando clima...",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        modifier = Modifier
                            .width(380.dp)
                            .height(80.dp)
                    ) {
                        items(imagesRow1.size) { index ->
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(2.dp, Color.Transparent, RoundedCornerShape(24.dp))
                                    .clickable {
                                        when (index) {
                                            0 -> navController.navigate("extra") // YouTube image
                                            1 -> navController.navigate("reproductor") // Spotify image
                                        }
                                    }
                            ) {
                                Image(
                                    painter = painterResource(id = imagesRow1[index]),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(10.dp))
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        modifier = Modifier
                            .width(380.dp)
                            .height(80.dp)
                    ) {
                        items(imagesRow2.size) { index ->
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(2.dp, Color.Transparent, RoundedCornerShape(24.dp))
                            ) {
                                Image(
                                    painter = painterResource(id = imagesRow2[index]),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(10.dp))
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Third LazyRow
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        modifier = Modifier
                            .width(380.dp)
                            .height(80.dp)
                    ) {
                        items(imagesRow3.size) { index ->
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(2.dp, Color.Transparent, RoundedCornerShape(24.dp))
                            ) {
                                Image(
                                    painter = painterResource(id = imagesRow3[index]),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(10.dp))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("dd 'de' MMMM 'del' yyyy, HH:mm", Locale("es", "MX"))
    return dateFormat.format(Date())
}
