package com.example.dhammapada.ui.composables.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dhammapada.ui.theme.PLACEHOLDER
import com.example.dhammapada.ui.theme.START
import com.example.dhammapada.ui.theme.TextStyle
import com.example.dhammapada.ui.viewmodel.DhammapadaViewModel

@Composable
fun MainScreen(viewModel: DhammapadaViewModel = viewModel()){

    val text by viewModel.currentText.collectAsState()
    val imageName by viewModel.currentImageName.collectAsState()

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val constraints = if (isLandscape) {
        landscapeConstraints()
    } else {
        portraitConstraints()
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .layoutId("imageBox")
                .size(400.dp),
            contentAlignment = Alignment.Center
        ) {
            ImageBox(imageName ?: PLACEHOLDER)
        }

        Box(
            modifier = Modifier
                .layoutId("textBox"),
            contentAlignment = Alignment.Center
        ) {
            Text(text ?: PLACEHOLDER,
                style = TextStyle)
        }
    }
}

private fun portraitConstraints(): ConstraintSet {
    return ConstraintSet {
        val imageBox = createRefFor("imageBox")
        val textBox = createRefFor("textBox")

        constrain(imageBox) {
            top.linkTo(parent.top, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(textBox) {
            top.linkTo(imageBox.bottom, margin = 80.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
    }
}

private fun landscapeConstraints(): ConstraintSet {
    return ConstraintSet {
        val imageBox = createRefFor("imageBox")
        val textBox = createRefFor("textBox")

        constrain(imageBox) {
            top.linkTo(parent.top, margin = 50.dp)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom, margin = 50.dp)
        }

        constrain(textBox) {
            top.linkTo(parent.top, margin = 50.dp)
            start.linkTo(imageBox.end, margin = 50.dp)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom, margin = 50.dp)
            width = Dimension.fillToConstraints
        }
    }
}