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

package cl.figonzal.aaid.ui.screens.settings

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContactSupport
import androidx.compose.material.icons.rounded.Policy
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.figonzal.aaid.BuildConfig
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.screens.core.BaseContainer
import cl.figonzal.aaid.ui.theme.AAIDTheme
import com.google.android.ump.ConsentInformation
import com.google.android.ump.UserMessagingPlatform


@Preview(
    showBackground = true, name = "About Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, name = "About Night"
)
@Composable
fun PreviewSettingsView() {
    AAIDTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            BaseContainer(
                modifier = Modifier.fillMaxSize()
            ) {
                SettingsView(onNavigateUp = {}, onDevContact = {}, onPrivacy = {})
            }
        }
    }
}

@Composable
fun SettingsView(
    onNavigateUp: () -> Unit,
    onDevContact: () -> Unit,
    onPrivacy: () -> Unit
) {

    BaseContainer(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                SettingsToolbar(
                    title = stringResource(R.string.settings_screen_title),
                    onNavigateUp = onNavigateUp
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            SettingsPreferenceList(onDevContact, onPrivacy, padding)
        }
    }

}

@Composable
private fun SettingsPreferenceList(
    onDevContact: () -> Unit,
    onPrivacy: () -> Unit,
    padding: PaddingValues
) {

    val context = LocalContext.current
    val consentInformation = UserMessagingPlatform.getConsentInformation(context)

    val isPrivacyOptionsRequired = consentInformation.privacyOptionsRequirementStatus ==
            ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {

            val aboutModifier: Modifier

            when {
                isPrivacyOptionsRequired -> {

                    item {
                        PreferenceCategory(
                            title = stringResource(R.string.privacy_preference_title),
                            icon = Icons.Rounded.Policy,
                            contentDescription = stringResource(R.string.cd_privacy_preference),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    item {
                        Preference(
                            title = stringResource(R.string.consent_privacy_preference_title),
                            subTitle = stringResource(R.string.conset_privacy_preference_subtitle),
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            isTitlePresent = true,
                            onClick = onPrivacy
                        )
                    }

                    item { Divider() }

                    aboutModifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                }

                else -> {
                    aboutModifier = Modifier.padding(bottom = 8.dp)
                }
            }

            item {
                PreferenceCategory(
                    title = stringResource(R.string.about_preference_title),
                    icon = Icons.Rounded.ContactSupport,
                    contentDescription = stringResource(id = R.string.cd_about),
                    modifier = aboutModifier
                )
            }

            item {
                Preference(
                    title = "",
                    subTitle = stringResource(R.string.about_app_description),
                    modifier = Modifier.padding(bottom = 8.dp),
                    isTitlePresent = false
                )
            }

            item { Divider() }

            item {
                Preference(
                    title = stringResource(R.string.version),
                    subTitle = BuildConfig.VERSION_NAME,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    isTitlePresent = true
                )
            }

            item { Divider() }

            item {
                Preference(
                    title = stringResource(R.string.contact_developer),
                    subTitle = stringResource(R.string.suggestions_problems),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 8.dp, bottom = 8.dp),
                    isTitlePresent = true,
                    onClick = onDevContact
                )
            }
        },
        contentPadding = padding
    )
}