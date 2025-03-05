package com.example.dhammapada.ui.composables.bottommenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dhammapada.ui.viewmodel.DhammapadaViewModel

@Composable
fun BottomMenu(viewModel: DhammapadaViewModel = viewModel()) {

    val currentId by viewModel.currentRecordId.collectAsState()

    Row(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Button(
            onClick = { viewModel.adviceFun() }
        )
        {
        Text("Получить совет")
        }

        IconButton(
            onClick = {
                if (currentId > 1) {
                    val newId = currentId - 1
                    viewModel.changeRecordId(newId)
                }
            },
            enabled = currentId > 1,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Blue,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.LightGray
            )
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
        }

        Text("$currentId/${viewModel.maxRecordId}")

        IconButton(
            onClick = {
                if (currentId < viewModel.maxRecordId) {
                    val newId = currentId + 1
                    viewModel.changeRecordId(newId)
                }
            },
            enabled = currentId < viewModel.maxRecordId,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Blue,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.LightGray
            )
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
        }

        DropdownMenu()
    }
}