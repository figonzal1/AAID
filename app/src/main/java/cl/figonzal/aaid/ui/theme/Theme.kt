/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2025
 *
 * Project: AAID
 * Module: AAID.app.main
 * Last modified: 07-03-25, 23:58
 */

package cl.figonzal.aaid.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val _darkColorScheme = darkColorScheme(
    primary = figonzalPurpleNight,
    secondary = figonzalAquaNight
)

private val _lightColorScheme = lightColorScheme(
    primary = figonzalPurple,
    secondary = figonzalAqua

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun AAIDTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> _darkColorScheme
        else -> _lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {

        SideEffect {
            val currentWindow = (view.context as? Activity)?.window
                ?: throw Exception("Not in an activity - unable to get Window reference")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) { // Android 15+
                currentWindow.decorView.setOnApplyWindowInsetsListener { view, insets ->
                    // Establece el color de fondo de la ventana
                    view.setBackgroundColor(colorScheme.primary.toArgb())

                    // Configura el tema claro/oscuro de la barra de estado
                    WindowCompat.getInsetsController(
                        currentWindow,
                        view
                    ).isAppearanceLightStatusBars =
                        !darkTheme

                    // No ajustes manualmente el padding, Compose lo maneja automáticamente
                    insets
                }
            } else {
                // Configura el color de la barra de estado (status bar)
                currentWindow.statusBarColor = colorScheme.primary.toArgb()

                WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars =
                    darkTheme
            }


        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}