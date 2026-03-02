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
 * Last modified: 06-03-25, 22:40
 */

package cl.figonzal.aaid.ui.screens.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import cl.figonzal.aaid.ui.screens.core.BaseContainer
import cl.figonzal.aaid.ui.theme.AAIDTheme

@Composable
fun MainScreen(viewModel: AAIDViewModel, onSettingsClick: () -> Unit) {

    BaseContainer(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        CardAAID(viewModel.state, onSettingsClick)
        Column(verticalArrangement = Arrangement.Bottom) {
            BannerView()
        }
    }

}

@Preview(showBackground = true, showSystemUi = true, name = "Main Screen Light")
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Main Screen Night"
)
@Composable
fun PreviewMainScreen() {

    AAIDTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                BaseContainer(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                ) {
                    CardAAID(AaidState.Success("91cf0b4c-578c-4e26-bb5a-10ca1ad1abe1")) {}
                    Column(verticalArrangement = Arrangement.Bottom) {
                        BannerView()
                    }
                }
            }
        }
    }

}
