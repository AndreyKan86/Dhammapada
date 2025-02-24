package com.example.dhammapada.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dhammapada.ui.theme.TITLE
import com.example.dhammapada.ui.theme.TitleTextStyle

@Composable
fun TitleTop() {
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.Blue),
        contentAlignment = Alignment.Center
    )
    {
        Text(text = TITLE,
            style = TitleTextStyle
        )
    }
}