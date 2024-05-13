package com.example.roverpath

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

// TODO: Więcej ścieżek

data class Trail(
    val name: String,
    val place: String,
    val length: Double,
    val stages: List<Stage>,
    val difficultyLevel: DifficultyLevel,
    val time: Double,
    val color: Color,
    val image: Painter
)

data class Stage(
    val name: String,
    val where: String,
)

enum class DifficultyLevel(val speed: Double) {
    EASY(1.3),
    NORMAL(1.0),
    HARD(0.7);
}

@Composable
fun getTrails(): List<Trail> {
    return listOf(
        Trail(
            name = "Wetlina-Smerek",
            place = "Bieszczady",
            length = 14.05,
            stages = listOf(
                Stage("Etap 1", "Smerek miejscowość - Smerek (1222)"),
                Stage("Etap 2", "Smerek (1222) - Osadzki Wierch (1253)"),
                Stage("Etap 3", "Osadzki Wierch (1253) - Połonina Wetlińska")
            ),
            difficultyLevel = DifficultyLevel.NORMAL,
            time = 6.5,
            color = Color.Red,
            image = painterResource(R.drawable.wetlina)
        ),
        Trail(
            name = "Wołosate - Tarnica",
            place = "Bieszczady",
            length = 5.00,
            stages = listOf(
                Stage("Etap 1", "Wołosate - Siadło pod Tarnicą"),
                Stage("Etap 2", "Siadło pod Tarnicą - Tarnica (1346)"),
            ),
            difficultyLevel = DifficultyLevel.EASY,
            time = 2.0,
            color = Color.Blue,
            image = painterResource(R.drawable.tarnica)
        ),
        Trail(
            name = "Połonina Caryńska",
            place = "Bieszczady",
            length = 8.90,
            stages = listOf(
                Stage("Etap 1", "Ustrzyki Górne - Kruhly Wierch (1297)"),
                Stage("Etap 2", "Kruhly Wierch (1297) - Brzegi Górne"),
            ),
            difficultyLevel = DifficultyLevel.EASY,
            time = 3.5,
            color = Color.Red,
            image = painterResource(R.drawable.carynska)
        ),
        Trail(
            name = "Bukowe Berdo",
            place = "Bieszczady",
            length = 7.00,
            stages = listOf(
                Stage("Etap 1", "Muczne - Berdo Borsukowe (1011)"),
                Stage("Etap 2", "Berdo Borsukowe (1011) - Bukowe Berdo (1311)"),
            ),
            difficultyLevel = DifficultyLevel.HARD,
            time = 4.5,
            color = Color.Blue,
            image = painterResource(R.drawable.berdo)
    )
    )
}
