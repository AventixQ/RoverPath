package com.example.roverpath

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//Kontent wyświetlany dla pozycji poziomej, na pozostałej szerokości ekranu
@Composable
fun DetailsContent(trail: Trail) {
    // Wypisywanie wszystkich szczegółów ścieżki
    Box(
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column (
            Modifier
                .fillMaxWidth()
        ){
            Text(
                text = trail.name,
                fontSize = 60.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Surface( //dla upiększenia
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = trail.place, fontSize = 40.sp)
                    Text(text = "Długość: ${trail.length}km", fontSize = 30.sp)
                    Text(text = "Poziom trudności: ${trail.difficultyLevel}", fontSize = 30.sp)
                    val dependTime = trail.difficultyLevel.speed * trail.time
                    Text(text = "Czas przejścia: ${dependTime}h", fontSize = 30.sp)
                    trail.stages.forEach { stage ->
                        Text(text = "${stage.name}: ${stage.where}", fontSize = 30.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp)) //dla upiększenia
            Surface(
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Stoper()
                }
            }
        }
    }
}


// Ekran ze szczegółami każdej ścieżki dla pozycji pionowej
@Composable
fun DetailsScreen(trailId: String?, navController: NavController) {
    val trail = getTrails().find { it.name == trailId } // Probrana ścieżka zależnie od jej nazwy
    trail?.let {// Wypisywanie wszystkich szczegółów ścieżki
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column (
                Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = trail.name,
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Surface(
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = trail.place, fontSize = 40.sp)
                        Text(text = "Długość: ${trail.length}km", fontSize = 30.sp)
                        Text(text = "Poziom trudności: ${trail.difficultyLevel}", fontSize = 30.sp)
                        val dependTime = trail.difficultyLevel.speed * trail.time
                        Text(text = "Czas przejścia: ${dependTime}h", fontSize = 30.sp)
                        trail.stages.forEach { stage ->
                            Text(text = "${stage.name}: ${stage.where}", fontSize = 30.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Surface(
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Stoper() //wyświetlanie stopera
                    }
                  }
                }
            }
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = { navController.navigate("main") }) { // przycisk poworotu do strony głównej
                Text("Wróć")}
        }
    }
}