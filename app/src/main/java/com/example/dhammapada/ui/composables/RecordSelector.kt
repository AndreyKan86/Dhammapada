package com.example.dhammapada.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlin.text.toIntOrNull

@Composable
fun RecordSelector(
    currentId: Int,
    onIdChange: (Int) -> Unit,
    maxId: Int
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(currentId.toString()))
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            onClick = {
                if (currentId > 1) {
                    val newId = currentId - 1
                    onIdChange(newId)
                    textFieldValue = TextFieldValue(newId.toString(), TextRange(newId.toString().length))
                }
            },
            enabled = currentId > 1
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
        }

        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newTextFieldValue ->
                textFieldValue = newTextFieldValue
                val newId = newTextFieldValue.text.toIntOrNull()
                if (newId == null) {
                    if (newTextFieldValue.text.isEmpty()) {
                        onIdChange(0)
                    }
                } else {
                    val clampedId = newId.coerceIn(1, maxId)
                    if (clampedId != newId) {
                        textFieldValue = TextFieldValue(clampedId.toString(), TextRange(clampedId.toString().length))
                    }
                    onIdChange(clampedId)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .width(80.dp)
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        textFieldValue = textFieldValue.copy(selection = TextRange(textFieldValue.text.length))
                    }
                },
            singleLine = true
        )

        IconButton(
            onClick = {
                if (currentId < maxId) {
                    val newId = currentId + 1
                    onIdChange(newId)
                    textFieldValue = TextFieldValue(newId.toString(), TextRange(newId.toString().length))
                }
            },
            enabled = currentId < maxId
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
        }
    }
}