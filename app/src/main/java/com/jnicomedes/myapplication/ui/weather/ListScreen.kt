package com.jnicomedes.myapplication.ui.weather

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListScreen(function: () -> Unit) {
    Text(text = "List Screen", modifier = Modifier.clickable {
        function.invoke()
    })
}