package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ElectricCyan,
    onPrimary = DarkNavyBackground,
    primaryContainer = DeepNavyCard,
    onPrimaryContainer = ElectricCyan,
    secondary = NeonIndigo,
    onSecondary = TextPrimary,
    secondaryContainer = DeepNavyCardBorder,
    onSecondaryContainer = TextPrimary,
    tertiary = StreakFlame,
    onTertiary = TextPrimary,
    background = DarkNavyBackground,
    onBackground = TextPrimary,
    surface = DeepNavySurface,
    onSurface = TextPrimary,
    surfaceVariant = DeepNavyCard,
    onSurfaceVariant = TextSecondary,
    outline = DeepNavyCardBorder,
    error = ErrorRed,
    onError = TextPrimary
)

@Composable
fun TarkoAITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            window?.let {
                it.statusBarColor = DarkNavyBackground.toArgb()
                it.navigationBarColor = DarkNavyBackground.toArgb()
                WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = false
                WindowCompat.getInsetsController(it, view).isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
