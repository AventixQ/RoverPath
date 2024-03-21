package com.example.roverpath

import android.health.connect.datatypes.units.Length
import androidx.compose.ui.graphics.Color

// TODO: Więcej ścieżek

data class Trail(
    val name: String,
    val place: String,
    val length: Double,
    val stages: List<Stage>,
    val difficultyLevel: DifficultyLevel,
    val time: Double,
    val color: Color
)

data class Stage(
    val name: String,
    val where: String,
)

enum class DifficultyLevel(val speed: Double) {
    EASY(1.3),
    NORMAL(1.0),
    HARD(0.7)
}

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
            color = Color.Red
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
            color = Color.Blue
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
            color = Color.Red
        )
    )
}
