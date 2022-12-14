package cl.figonzal.aaid.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContactSupport
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.figonzal.aaid.R

@Preview(showBackground = true)
@Composable
fun PreviewPreferenceCategory() {
    PreferenceCategory("Acerca")
}

@Composable
fun PreferenceCategory(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.ContactSupport,
                contentDescription = stringResource(R.string.cd_about)
            )
        }

        Column(
            modifier = modifier.padding(start = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(title, color = MaterialTheme.colorScheme.primary)
        }
    }
}
