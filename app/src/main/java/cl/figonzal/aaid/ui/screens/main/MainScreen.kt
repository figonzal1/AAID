/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2022
 *
 * Project: AAID
 * Module: AAID.app.main
 * Last modified: 17-12-22 13:18
 */

package cl.figonzal.aaid.ui.screens.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.figonzal.aaid.ui.screens.core.BaseContainer
import cl.figonzal.aaid.ui.theme.AAIDTheme

@Composable
fun MainScreen(viewModel: AAIDViewModel, onSettingsClick: () -> Unit) {

    BaseContainer(
        modifier = Modifier.fillMaxSize()
    ) {
        CardAAID(viewModel.aaid, onSettingsClick)
        Column(verticalArrangement = Arrangement.Bottom) {
            //BannerView()
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
            BaseContainer(
                modifier = Modifier.fillMaxSize()
            ) {
                CardAAID("91cf0b4c-578c-4e26-bb5a-10ca1ad1abe1") {}
                Column(verticalArrangement = Arrangement.Bottom) {
                    BannerView()
                }
            }
        }
    }

}