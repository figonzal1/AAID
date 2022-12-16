package cl.figonzal.aaid.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.figonzal.aaid.R

@Preview(showBackground = true)
@Composable
fun PreviewToolbar() {
    SettingsToolbar("Configuración") {}
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
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.cd_navigate_up)
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                paddingValues = WindowInsets.statusBars
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                    .asPaddingValues()
            )
    )
}
