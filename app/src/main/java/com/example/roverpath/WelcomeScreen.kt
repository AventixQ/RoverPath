package com.example.roverpath

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun drawSinusoid(divide: Float, color: Color, freq: Float, phase: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val amplitude = canvasHeight / divide
        val frequency = freq / canvasWidth
        drawPath(
            color = color,
            path = Path().apply {
                moveTo(0f, canvasHeight)
                lineTo(0f, canvasHeight - amplitude * (1 + sin(phase)))
                for (x in 1 until canvasWidth.toInt()) {
                    lineTo(x.toFloat(), canvasHeight - amplitude * (1 + sin(frequency * x + phase)))
                }
                lineTo(canvasWidth, canvasHeight)
                close()
            },
            style = Fill
        )
    }
}

@Composable
fun WelcomeScreen() {
    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )



    drawSinusoid(divide = 4f, color = Color.LightGray, freq = 1.0f * PI.toFloat(), phase = phase)
    drawSinusoid(divide = 6f, color = Color.DarkGray, freq = 2.0f * PI.toFloat(), phase = phase)
    drawSinusoid(divide = 8f, color = Color.Gray, freq = 3.0f * PI.toFloat(), phase = phase)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Drogi użytkowniku!",
            style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Witaj w aplikacji RoverApp, która pomoże ci się sprawnie poruszać po szlakach górskich.",
            style = TextStyle(fontSize = 30.sp)
        )
    }
}