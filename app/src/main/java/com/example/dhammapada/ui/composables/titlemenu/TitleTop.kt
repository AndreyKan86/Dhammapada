package com.example.dhammapada.ui.composables.titlemenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dhammapada.ui.theme.TITLE
import com.example.dhammapada.ui.theme.TitleTextStyle

@Composable
fun TitleTop() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    )
    {
        Text(text = TITLE,
            style = TitleTextStyle
        )
    }
}