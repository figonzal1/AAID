package cl.figonzal.aaid.ui.settings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.figonzal.aaid.BuildConfig
import cl.figonzal.aaid.R
import cl.figonzal.aaid.SettingsToolbar


@Preview(showBackground = true)
@Composable
fun PreviewSettingsView() {
    SettingsView(onNavigateUp = {}, onDevContact = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(
    onNavigateUp: () -> Unit,
    onDevContact: () -> Unit
) {

    Scaffold(
        topBar = {
            SettingsToolbar(title = "Settings", onNavigateUp = onNavigateUp)
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        SettingsPreferenceList(onDevContact, padding)
    }

}

@Composable
private fun SettingsPreferenceList(
    onDevContact: () -> Unit,
    padding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {

            item {
                PreferenceCategory(
                    title = stringResource(R.string.about_preference_title),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                Preference(
                    title = "",
                    subTitle = stringResource(R.string.about_app_description),
                    isTitlePresent = false,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) { }
            }

            item { Divider() }

            item {
                Preference(
                    title = stringResource(R.string.version),
                    subTitle = BuildConfig.VERSION_NAME,
                    isTitlePresent = true,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ) { }
            }

            item { Divider() }

            item {
                Preference(
                    title = stringResource(R.string.contact_developer),
                    subTitle = stringResource(R.string.suggestions_problems),
                    onClick = onDevContact,
                    isTitlePresent = true,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 8.dp, bottom = 8.dp)
                )
            }
        },
        contentPadding = padding
    )
}