package com.example.roverpath

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun Stoper() { //stoper z 3 przyciskami: Start | Stop | Reset
    var time by remember { mutableStateOf(0) }
    var running by remember { mutableStateOf(false) }
    var times by remember { mutableStateOf(listOf<Int>()) }

    LaunchedEffect(running) {
        while (running) {
            delay(1000)
            time++
        }
    }

    Column {
        Text(text = "Czas: ${time}s", fontSize = 30.sp)
        Row {
            Button(onClick = { running = true }) {
                Text("Start")
            }
            Button(onClick = { running = false }) {
                Text("Stop")
            }
            Button(onClick = { //Reset czyści wyniki i dodaje czas do times
                running = false
                times = times + time
                time = 0
            }) {
                Text("Reset")
            }
        }
        Text(text = "Zapisane czasy:", fontSize = 20.sp)
        times.forEach { savedTime ->
            Text(text = "${savedTime}s", fontSize = 15.sp)
        }
    }
}