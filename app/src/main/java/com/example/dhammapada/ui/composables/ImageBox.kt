package com.example.dhammapada.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dhammapada.R

@Composable
fun ImageBox() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground), 
        contentDescription = "Изображение",
        modifier = Modifier.size(100.dp)
    )
}
