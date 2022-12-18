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
 * Last modified: 15-12-22 22:46
 */

package cl.figonzal.aaid.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContactSupport
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun PreviewPreference() {
    Column {
        Preference(
            "Contacto desarrollador",
            "¿Sugerencias? ¿Problemas?", isTitlePresent = true
        ) {}
    }
}

@Composable
fun Preference(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    isTitlePresent: Boolean = false,
    onClick: () -> Unit
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
                modifier = Modifier.alpha(0f),
                imageVector = Icons.Rounded.ContactSupport,
                contentDescription = null
            )
        }

        Column(
            modifier = modifier.padding(start = 32.dp),
            verticalArrangement = Arrangement.Top
        ) {
            if (isTitlePresent) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = subTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}
