package com.example.dhammapada.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MainScreen(text: String, imageName: String?){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
        val (imageBox, textBox) = createRefs()

        Box(modifier = Modifier
            .constrainAs(imageBox){
            top.linkTo(parent.top, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
            .size(400.dp)
            .background(Color.Blue),
            contentAlignment = Alignment.Center
        )
        {
            ImageBox(imageName)
            //var textToDisplay = imageName ?: "Данные отсутствуют"
            //Text(text = textToDisplay)
        }

        Box(modifier = Modifier.constrainAs(textBox){
            top.linkTo(imageBox.bottom, margin = 80.dp)
            start.linkTo(parent.start)
        }
            .fillMaxWidth()
            .background(Color.Cyan),
            contentAlignment = Alignment.Center
        )
        {
            Text(text)
        }
    }
}