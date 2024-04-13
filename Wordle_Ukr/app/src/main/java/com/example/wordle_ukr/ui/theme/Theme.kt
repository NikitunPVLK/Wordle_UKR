package com.example.wordle_ukr.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = DarkBackground,
    primary = DarkPrimary,
    onPrimary = DarkTextColor,
    primaryContainer = DarkPrimary,
    onPrimaryContainer = TextColor,
    error = MistakenCharDarkColor,
    onError = DarkTextColor,
    secondary = CorrectCharColor,
    onSecondary = DarkTextColor,
    tertiary = CharDispositionColor,
    onTertiary = DarkTextColor
)

private val LightColorScheme = lightColorScheme(
    background = Color.White,
    primary = KeyColor,
    onPrimary = TextColor,
    primaryContainer = Color.White,
    onPrimaryContainer = TextColor,
    error = MistakenCharLightColor,
    onError = TextColor,
    secondary = CorrectCharColor,
    onSecondary = TextColor,
    tertiary = CharDispositionColor,
    onTertiary = TextColor
)

@Composable
fun WordleUkrTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}