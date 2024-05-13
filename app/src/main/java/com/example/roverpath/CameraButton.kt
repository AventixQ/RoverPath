package com.example.roverpath

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.outlined.MotionPhotosOn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CameraButton() {
    val takePicture = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            // Tutaj możesz zrobić coś z bitmapą, np. wyświetlić ją na ekranie lub zapisać w pamięci urządzenia
            Log.d("Camera", "Zdjęcie zostało zrobione")
        } else {
            Log.d("Camera", "Nie udało się zrobić zdjęcia")
        }
    }

    FloatingActionButton(onClick = { takePicture.launch() }) {
        Icon(Icons.Outlined.MotionPhotosOn, contentDescription = "Uruchom aparat fotograficzny", modifier = Modifier.size(70.dp))
    }
}
