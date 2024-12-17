package com.example.littlelemon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Primary1,
    secondary = Primary2,
    tertiary = Highlight1,
    background = Color(0xFF333333),
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary1,
    secondary = Primary2,
    tertiary = Highlight1,
    background = Color(0xFFFFFFFF),
    onSurface = Color.Black
)

@Composable
fun LittleLemonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}