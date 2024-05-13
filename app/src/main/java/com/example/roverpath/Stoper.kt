

package com.example.roverpath

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun Stoper() { //stoper z 3 przyciskami: Start | Stop | Reset
    var time by rememberSaveable { mutableStateOf(0) }
    var running by rememberSaveable { mutableStateOf(false) }
    var times by rememberSaveable { mutableStateOf(listOf<Int>()) }

    LaunchedEffect(running) {
        while (running) {
            delay(1000)
            time++
        }
    }

    Column {
        Text(text = "Czas: ${time}s", fontSize = 40.sp)
        Row {
            Button(onClick = { running = true }) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Localized description"
                )
            }
            Button(onClick = { running = false }) {
                Icon(
                    imageVector = Icons.Filled.Pause,
                    contentDescription = "Localized description"
                )
            }
            Button(onClick = { //Reset czyści wyniki i dodaje czas do times
                running = false
                times = times + time
                time = 0
            }) {
                Icon(
                    imageVector = Icons.Filled.RestartAlt,
                    contentDescription = "Localized description"
                )
            }
        }
        Text(text = "Zapisane czasy:", fontSize = 20.sp)
        times.forEach { savedTime ->
            Text(text = "${savedTime}s", fontSize = 30.sp)
        }
    }
}