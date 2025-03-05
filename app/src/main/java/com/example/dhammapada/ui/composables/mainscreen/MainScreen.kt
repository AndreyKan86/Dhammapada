package com.example.dhammapada.ui.composables.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dhammapada.ui.viewmodel.DhammapadaViewModel

@Composable
fun MainScreen(dhammapadaViewModel: DhammapadaViewModel = viewModel()){

    val text by dhammapadaViewModel.currentText.collectAsState()
    val imageName by dhammapadaViewModel.currentImageName.collectAsState()

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
            ImageBox(imageName ?: "Ошибка")
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
            Text(text ?: "Ошибка")
        }
    }
}