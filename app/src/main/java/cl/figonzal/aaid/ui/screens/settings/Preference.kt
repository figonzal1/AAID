/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2023
 *
 * Project: AAID
 * Module: AAID.app.main
 * Last modified: 13-01-23 17:48
 */

package cl.figonzal.aaid.ui.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Abc
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.figonzal.aaid.ui.theme.AAIDTheme


@Preview(showBackground = true, name = "Preference Light")
@Preview(
    name = "Preference Dark", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewPreference() {
    AAIDTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Preference(
                    title = "Contacto desarrollador",
                    subTitle = "¿Sugerencias? ¿Problemas?",
                    isTitlePresent = true
                )
            }
        }
    }
}

@Composable
fun Preference(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    isTitlePresent: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = 80.dp,
                    color = Color.Black
                ),
                enabled = true,
                onClick = onClick
            )

    ) {

        Column(
            modifier = modifier.padding(start = 16.dp)
        ) {
            Icon(
                modifier = Modifier.alpha(0f), //invisible
                imageVector = Icons.Rounded.Abc,
                contentDescription = null
            )
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            if (isTitlePresent) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = subTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
