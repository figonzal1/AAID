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
 * Last modified: 06-03-25, 22:53
 */

package cl.figonzal.aaid.ui.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.theme.AAIDTheme

@Preview(
    showBackground = true, name = "Setting Toolbar Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true, name = "Setting Toolbar Night",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewToolbar() {
    AAIDTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            SettingsToolbar("Configuración") {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsToolbar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = title, modifier = modifier.padding(start = 16.dp))
        },
        navigationIcon = {
            IconButton(onClick = { onNavigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.cd_navigate_up)
                )
            }
        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars) // Asegúrate de que el TopAppBar respete los insets

    )
}
