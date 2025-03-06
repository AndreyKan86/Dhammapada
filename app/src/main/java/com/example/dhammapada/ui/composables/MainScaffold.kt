package com.example.dhammapada.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dhammapada.ui.composables.bottommenu.BottomMenu
import com.example.dhammapada.ui.composables.drawermenu.DrawerContent
import com.example.dhammapada.ui.composables.drawermenu.DrawerMenuBottom
import com.example.dhammapada.ui.composables.mainscreen.MainScreen
import com.example.dhammapada.ui.composables.titlemenu.TitleTop
import com.example.dhammapada.ui.viewmodel.DhammapadaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(dhammapadaViewModel: DhammapadaViewModel = viewModel()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

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
                        BottomMenu()
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
                    MainScreen()
                }
            }
        }
    )
}