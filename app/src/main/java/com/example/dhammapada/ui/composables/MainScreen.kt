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
fun MainScreen(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
        val (image, text) = createRefs()

        Box(modifier = Modifier
            .constrainAs(image){
            top.linkTo(parent.top, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
            .size(400.dp)
            .background(Color.Blue),
            contentAlignment = Alignment.Center
        )
        {
            ImageBox()
        }

        Box(modifier = Modifier.constrainAs(text){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
        }
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            Text("Текст")
        }
    }
}