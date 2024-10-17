package com.example.producto_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost

@Composable
fun CuentosScreen(index: Int, navController: NavController) {
    val stories = listOf(
        "La Aventura de Leo" to "Había una vez un valiente león que vivía en el corazón de un vasto bosque. Su vida era tranquila hasta que un día, un gran peligro se cernió sobre su hogar. El león decidió aventurarse más allá de su territorio para encontrar una solución. En su viaje, encontró nuevos amigos, como un astuto zorro y una sabia tortuga, que le ofrecieron su ayuda. Juntos enfrentaron numerosos desafíos, como atravesar ríos caudalosos y escalar montañas escarpadas. Finalmente, con la ayuda de sus nuevos amigos, el león logró salvar su hogar y restaurar la paz en el bosque. A través de su valentía y la amistad que forjó, descubrió el verdadero significado del coraje. Al regresar, fue recibido como un héroe por todos los habitantes del bosque.",
        "La Ciudad Perdida" to "En un rincón olvidado del mundo, se encontraba una ciudad perdida, llena de tesoros y secretos, oculta entre la vegetación densa de la selva. La leyenda decía que solo los más valientes podrían encontrarla. Un grupo de exploradores decidió embarcarse en una misión para descubrir la ciudad perdida. A medida que avanzaban, enfrentaron trampas antiguas y desentrañaron misterios. Cada obstáculo que superaban les enseñaba valiosas lecciones sobre trabajo en equipo y perseverancia. Al final, descubrieron que la verdadera riqueza no estaba en los tesoros materiales, sino en las experiencias vividas y las lecciones aprendidas durante el viaje. Regresaron a casa con una nueva perspectiva sobre lo que significa ser verdaderamente rico.",
        "El Secreto del Viejo Molino" to "En un viejo molino al borde del pueblo, se escondía un antiguo secreto que había sido olvidado por todos. La gente del pueblo evitaba el molino por miedo a lo desconocido. Un joven curioso decidió investigar el molino para desentrañar el misterio. Al explorar, encontró viejos diarios y objetos que revelaban una historia de amor y sacrificio. El secreto del molino era un legado dejado por una pareja que había protegido al pueblo de una gran amenaza. El joven compartió el legado con el pueblo, y todos se dieron cuenta de que el molino era un símbolo de valor y amor. El pueblo decidió restaurarlo y convertirlo en un lugar de memoria y enseñanza para las futuras generaciones."
    )

    val (title, story) = stories.getOrNull(index) ?: "Historia no encontrada" to "Contenido no disponible"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = story,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = when (index) {
                    0 -> R.drawable.foto1
                    1 -> R.drawable.foto2
                    2 -> R.drawable.foto3
                    else -> R.drawable.background_image
                }),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 16.dp)
            )
        }
    }
}
