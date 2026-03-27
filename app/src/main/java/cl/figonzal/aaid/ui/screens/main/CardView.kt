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

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.Clipboard
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.figonzal.aaid.BuildConfig
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.theme.AAIDTheme
import cl.figonzal.aaid.utils.copyToClipBoard
import cl.figonzal.aaid.utils.shareAAID
import cl.figonzal.aaid.utils.toast
import kotlinx.coroutines.launch

@Preview(
    showBackground = true,
    name = "CardView Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
    name = "CardView Night",
)
@Composable
fun DefaultCardAAID() {
    AAIDTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            CardAAID(AaidState.Success("91cf0b4c-578c-4e26-bb5a-10ca1ad1abe1")) {}
        }
    }
}

@Composable
fun CardAAID(
    state: AaidState,
    onSettingsClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            CardContent(state, onSettingsClick)
        }
    }
}

@Composable
private fun CardContent(
    state: AaidState,
    onSettingsClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        val context = LocalContext.current
        val clipboard = LocalClipboard.current

        // Header section
        Box {
            Image(
                painterResource(R.drawable.undraw_android_jr64),
                contentDescription = stringResource(R.string.cd_background_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit,
            )

            Column {
                Text(
                    stringResource(R.string.cv_title),
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "v${BuildConfig.VERSION_NAME}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                verticalArrangement = Arrangement.Top,
            ) {
                IconButton(
                    onClick = onSettingsClick,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = stringResource(R.string.cd_settings_button),
                    )
                }
            }
        }

        // Subtitle
        Text(
            text = stringResource(R.string.cv_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        when (state) {
            is AaidState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }

            is AaidState.Error -> {
                Text(
                    text = state.message,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 32.dp),
                )
            }

            is AaidState.Success -> {
                Text(
                    text = state.aaid,
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 32.dp),
                )
                ActionsButtons(context, clipboard, state.aaid)
            }
        }
    }
}

@Composable
private fun ActionsButtons(context: Context, clipboard: Clipboard, aaid: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End,
    ) {
        ClipBoardButton(context, clipboard, aaid)
        ShareButton(context, clipboard, aaid)
    }
}

@Composable
private fun ClipBoardButton(
    context: Context,
    clipboard: Clipboard,
    aaid: String,
) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                copyToClipBoard(clipboard, aaid)
                context.toast(R.string.copy_to_clipboard)
            }
        },
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        modifier = Modifier.padding(end = 8.dp),
    ) {
        Icon(Icons.Rounded.ContentCopy, stringResource(R.string.cd_copy_btn))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.btn_copy))
    }
}

@Composable
private fun ShareButton(
    context: Context,
    clipboard: Clipboard,
    aaid: String,
) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                copyToClipBoard(clipboard, aaid)
                context.shareAAID(aaid)
            }
        },
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
    ) {
        Icon(Icons.Rounded.Share, stringResource(R.string.cd_share_id_btn))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.btn_share))
    }
}
