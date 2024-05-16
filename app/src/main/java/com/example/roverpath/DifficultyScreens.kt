package com.example.roverpath

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment

// Główny ekran z wypisanymi wszystkimi ścieżkami
@Composable
fun DifficultyScreen(navController: NavController, difficultyLevel: DifficultyLevel) {
    val trails = getTrails().filter { it.difficultyLevel == difficultyLevel }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.padding(top = 60.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(trails) { trail ->
            Card(
                modifier = Modifier
                    .clickable { navController.navigate("details/${trail.name}") } // Przycisk, po wciśnięciu przjeście do DetailsScreen
                    .padding(8.dp)
            ) {
                Column {
                    Image(painter = trail.image, contentDescription = "Opis obrazka")
                }
            }
        }
    }
}

@Composable
fun DifficultyTabletScreen(navController: NavController, difficultyLevel: DifficultyLevel) {
    val trails = getTrails().filter { it.difficultyLevel == difficultyLevel }
    var selectedTrail by remember { mutableStateOf<Trail?>(null) } // Stan dla wybranego szlaku
    Row(Modifier.fillMaxSize()) { // Dodajemy Row, aby podzielić ekran na dwie części
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                //.padding(20.dp)
                //.padding(top = 20.dp)
                .fillMaxWidth(0.35f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(trails) { trail ->
                Card(
                    modifier = Modifier
                        .clickable {
                            selectedTrail = trail
                        } // Przycisk, po wciśnięciu przjeście do DetailsScreen
                        .padding(5.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Image(painter = trail.image, contentDescription = "Opis obrazka")
                    }
                }
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .padding(top = 20.dp)
        ) { // Druga połowa ekranu dla szczegółów szlaku
            selectedTrail?.let { trail ->
                DetailsContent(trail, navController) // Wyświetlamy szczegóły wybranego szlaku
            }
        }
    }
}