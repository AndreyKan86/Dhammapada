package com.example.dhammapada.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Цветовая схема для светлой темы
val LightColorScheme = lightColorScheme(
    primary =  Color(0xFFFFA725),     // Основной цвет
    secondary = Color(0xFF625B71),     // Вторичный цвет
    tertiary = Color(0xFF7D5260),      // Третичный цвет
    background = Color(0xFFFFF5E4),    // Фон приложения
    surface = Color(0xFFF9CB43),        // Поверхность
    onPrimary = Color.White,          // Текст/иконки на primary
    onSecondary = Color.White,        // Текст/иконки на secondary
    onTertiary = Color.White,         // Текст/иконки на tertiary
    onBackground = Color(0xFF1C1B1F),  // Текст/иконки на фоне
    onSurface = Color(0xFF1C1B1F),     // Текст/иконки на поверхности
)

// Цветовая схема для темной темы
val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFDD98324),       // Основной цвет
    secondary = Color(0xFFCCC2DC),     // Вторичный цвет
    tertiary = Color(0xFFEFB8C8),      // Третичный цвет
    background = Color.DarkGray,  // Фон приложения
    surface = Color.DarkGray,        // Поверхность
    onPrimary = Color(0xFFEFDCAB),     // Текст/иконки на primary
    onSecondary = Color(0xFF332D41),   // Текст/иконки на secondary
    onTertiary = Color(0xFF492532),    // Текст/иконки на tertiary
    onBackground = Color(0xFFE6E1E5),  // Текст/иконки на фоне
    onSurface = Color(0xFFE6E1E5),     // Текст/иконки на поверхности
)

object ThemeManager {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }
}

@Composable
fun DhammapadaTheme(
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val isDarkTheme by ThemeManager.isDarkTheme.collectAsState()

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}