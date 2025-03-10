package com.example.dhammapada.ui.composables.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dhammapada.ui.theme.PLACEHOLDER_IMAGE
import com.example.dhammapada.ui.theme.PLACEHOLDER_TEXT
import com.example.dhammapada.ui.theme.TextStyle
import com.example.dhammapada.ui.viewmodel.DhammapadaViewModel

@Composable
fun MainScreen(viewModel: DhammapadaViewModel = viewModel()) {

    val text by viewModel.currentText.collectAsState()
    val imageName by viewModel.currentImageName.collectAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val constraints = if (isLandscape) {
        landscapeConstraints(screenWidth, screenHeight)
    } else {
        portraitConstraints(screenWidth, screenHeight)
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .layoutId("imageBox")
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            ImageBox(imageName ?: PLACEHOLDER_IMAGE)
        }

        Box(
            modifier = Modifier
                .layoutId("textBox")
                .border(2.dp, Color(0xFF2C3930), RoundedCornerShape(16.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text ?: PLACEHOLDER_TEXT,
                style = TextStyle
            )
        }
    }
}

private fun portraitConstraints(screenWidth: Dp, screenHeight: Dp): ConstraintSet {
    return ConstraintSet {
        val imageBox = createRefFor("imageBox")
        val textBox = createRefFor("textBox")
        val minScreenSize = min(screenWidth, screenHeight)
        val boxSize = Dimension.value(minScreenSize * 0.85f)

        constrain(imageBox) {
            top.linkTo(parent.top, margin = (screenHeight * 0.01f))
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = boxSize
            height = boxSize
        }

        constrain(textBox) {
            top.linkTo(imageBox.bottom, margin = (screenHeight * 0.01f))
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = boxSize
        }
    }
}

private fun landscapeConstraints(screenWidth: Dp, screenHeight: Dp): ConstraintSet {
    return ConstraintSet {
        val imageBox = createRefFor("imageBox")
        val textBox = createRefFor("textBox")
        val minScreenSize = min(screenWidth, screenHeight)
        val boxSize = Dimension.value(minScreenSize * 0.6f)

        constrain(imageBox) {
            top.linkTo(parent.top, margin = (screenHeight * 0.01f))
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            width = boxSize
            height = boxSize
        }

        constrain(textBox) {
            top.linkTo(parent.top, margin = (screenHeight * 0.05f))
            start.linkTo(imageBox.end, margin = (screenWidth * 0.05f))
            end.linkTo(parent.end, margin = (screenWidth * 0.05f))
            bottom.linkTo(parent.bottom, margin = (screenHeight * 0.05f))
            width = Dimension.fillToConstraints
        }
    }
}