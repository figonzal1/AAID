/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2026
 *
 * Project: AAID
 * Module: AAID.app.main
 * Last modified: 02-03-26, 20:45
 */

package cl.figonzal.aaid.ui.screens.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import cl.figonzal.aaid.ui.screens.core.BaseContainer
import cl.figonzal.aaid.ui.theme.AAIDTheme

@Composable
fun MainScreen(viewModel: AAIDViewModel, onSettingsClick: () -> Unit) {

    BaseContainer(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentWindowInsets = WindowInsets.systemBars
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CardAAID(viewModel.state, onSettingsClick)
                }
                BannerView()
            }
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
                BaseContainer(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        contentWindowInsets = WindowInsets.systemBars
                    ) { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CardAAID(AaidState.Success("91cf0b4c-578c-4e26-bb5a-10ca1ad1abe1")) {}
                            }
                            BannerView()
                        }
                    }
                }
            }
        }
    }

}
