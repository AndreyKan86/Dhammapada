package com.example.dhammapada.ui.composables.bottommenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dhammapada.ui.viewmodel.DhammapadaViewModel

@Composable
fun DropdownMenu(viewModel: DhammapadaViewModel = viewModel()) {
    var expanded by remember { mutableStateOf(false) }
    var selectedRecord by remember { mutableStateOf(1) }
    val record =  (1..422).toList()

    Card(modifier = Modifier.width(80.dp))
    {
        Text(
            text = selectedRecord.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(10.dp)
                .padding(horizontal = 8.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            record.forEach { number ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = number.toString(),
                            modifier = Modifier
                                .clickable {
                                    selectedRecord = number
                                    expanded = false
                                    viewModel.changeRecordId(number)
                                }
                                .padding(10.dp)
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth()
                        )
                    },
                    onClick = { }
                )
            }
        }
    }
}