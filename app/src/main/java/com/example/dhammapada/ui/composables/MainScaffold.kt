package com.example.dhammapada.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dhammapada.ui.theme.MENU_DESCRIPTION
import com.example.dhammapada.ui.viewmodel.DhammapadaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(dhammapadaViewModel: DhammapadaViewModel = viewModel()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var imageName by remember { mutableStateOf<String?>(null) }
    val records by dhammapadaViewModel._records.collectAsState()
    val currentRecordId by dhammapadaViewModel.currentRecordId.collectAsState()
    val maxRecordId = dhammapadaViewModel.maxRecordId

    LaunchedEffect(Unit) {
        dhammapadaViewModel.loadData(context)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent { scope.launch { drawerState.close() } }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Green
                        ),
                        title = {
                            TitleTop()
                        },
                        navigationIcon = {
                            DrawerMenuBottom(drawerState, scope)
                        }
                    )
                },
                bottomBar = {
                    BottomAppBar(
                        containerColor = Color.Yellow,
                    ) {
                        Button(onClick = {
                            try {
                                text = dhammapadaViewModel.getTextById(currentRecordId) ?: "Not found"
                                imageName = dhammapadaViewModel.getImageById(currentRecordId)
                            } catch (e: Exception) {
                                text = "Error"
                                imageName = "Error"
                            }
                        }) {
                            Text("Старт")
                        }

                        RecordSelector(
                            currentId = currentRecordId,
                            onIdChange = { dhammapadaViewModel.changeRecordId(it) },
                            maxId = maxRecordId
                        )
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .background(color = Color.Red)
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)

                ) {
                    MainScreen(text, imageName)
                }
            }
        }
    )
}