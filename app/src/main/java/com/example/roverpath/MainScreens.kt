package com.example.roverpath

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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
                    .border(
                        4.dp,
                        trail.color,
                        RoundedCornerShape(4.dp)
                    ) // KOLORKI szlaków jako otoczka
                    .padding(vertical = 16.dp)){
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = trail.name + "\n" + trail.place,
                    fontSize = 50.sp,
                    modifier = Modifier
                        .clickable { navController.navigate("details/${trail.name}") } // Przycisk, po wciśnięciu przjeście do DetailsScreen
                        .padding(16.dp)

                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun MainTabletScreen(navController: NavController) {
    val trails = getTrails() // Pobieramy ścieżki
    var selectedTrail by remember { mutableStateOf<Trail?>(null) } // Stan dla wybranego szlaku

    Row(Modifier.fillMaxSize()) { // Dodajemy Row, aby podzielić ekran na dwie części
        LazyColumn( // Składamy ścieżki w kolumny
            Modifier
                .fillMaxWidth(0.4f) // Zajmuje tylko połowę szerokości ekranu
                .systemBarsPadding()
        ){
            items(trails) { trail -> // Dla każdej ścieżki trail wypisujemy nazwe + miejsce
                Row(
                    Modifier
                        .fillMaxWidth()
                        .border(
                            4.dp,
                            trail.color,
                            RoundedCornerShape(4.dp)
                        ) // KOLORKI szlaków jako otoczka
                        .padding(vertical = 16.dp)){
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = trail.name + "\n" + trail.place,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .clickable {
                                selectedTrail = trail
                            } // Aktualizujemy stan wybranego szlaku
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) { // Druga połowa ekranu dla szczegółów szlaku
            selectedTrail?.let { trail ->
                DetailsContent(trail) // Wyświetlamy szczegóły wybranego szlaku
            }
        }
    }
}