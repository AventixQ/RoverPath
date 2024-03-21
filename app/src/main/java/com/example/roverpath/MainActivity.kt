package com.example.roverpath

import android.R
import android.R.id
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// TODO: Fragmenty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("details/{trailId}") { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString("trailId"),navController)
        }
    }
}

// Główny ekran z wypisanymi wszystkimi ścieżkami
@Composable
fun MainScreen(navController: NavController) {
    val trails = getTrails() // Pobieramy ścieżki
    LazyColumn( // Składamy ścieżki w kolumny
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ){
        items(trails) { trail -> // Dla każdej ścieżki trail wypisujemy nazwe + miejsce
            Row(
                Modifier
                    .fillMaxWidth()
                    .border(4.dp, trail.color, RoundedCornerShape(4.dp)) // KOLORKI szlaków jako otoczka
                    .padding(vertical = 8.dp)){
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = trail.name + "\n" + trail.place,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .clickable { navController.navigate("details/${trail.name}") } // Przycisk, po wciśnięciu przjeście do DetailsScreen
                        .padding(16.dp)

                        .fillMaxWidth()
                )
            }
        }
    }
}

// Ekran ze szczegółami każdej ścieżki
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
                    text = it.name,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(text = it.place, fontSize = 20.sp)
                Text(text = "Długość: ${it.length}km", fontSize = 15.sp)
                Text(text = "Poziom trudności: ${it.difficultyLevel}", fontSize = 15.sp)
                val dependTime = it.difficultyLevel.speed * it.time
                Text(text = "Czas przejścia: ${dependTime}h", fontSize = 15.sp)
                it.stages.forEach { stage ->
                    Text(text = "${stage.name}: ${stage.where}", fontSize = 15.sp)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}
