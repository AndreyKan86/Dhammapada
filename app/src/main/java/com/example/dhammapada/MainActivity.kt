package com.example.dhammapada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dhammapada.ui.composables.MainScaffold
import com.example.dhammapada.ui.theme.DhammapadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DhammapadaTheme {
                    MainScaffold()
                }
            }
        }
    }


@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    DhammapadaTheme {
        MainScaffold()
    }
}