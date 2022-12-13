package cl.figonzal.aaid.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Help
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cl.figonzal.aaid.R


@Composable
fun SettingsView() {
    Row {
        Icon(
            imageVector = Icons.Rounded.Help,
            contentDescription = stringResource(R.string.cd_about_button)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSettingsView() {
    Row {
        Icon(
            imageVector = Icons.Rounded.Help,
            contentDescription = stringResource(R.string.cd_about_button)
        )
    }
}