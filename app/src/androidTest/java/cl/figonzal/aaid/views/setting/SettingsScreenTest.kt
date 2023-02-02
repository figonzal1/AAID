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
 * Module: AAID.app.androidTest
 * Last modified: 17-12-22 12:03
 */

package cl.figonzal.aaid.views.setting

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import cl.figonzal.aaid.BuildConfig
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.screens.settings.SettingsView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy
import tools.fastlane.screengrab.cleanstatusbar.BluetoothState
import tools.fastlane.screengrab.cleanstatusbar.CleanStatusBar
import tools.fastlane.screengrab.cleanstatusbar.MobileDataType
import tools.fastlane.screengrab.locale.LocaleTestRule

class SettingsScreenTest {

    @Rule
    @JvmField
    val localeTestRule = LocaleTestRule()

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setupAppNavHost() {

        Screengrab.setDefaultScreenshotStrategy(
            UiAutomatorScreenshotStrategy()
        )
        CleanStatusBar()
            .setBluetoothState(BluetoothState.DISCONNECTED)
            .setMobileNetworkDataType(MobileDataType.LTE)
            .setClock("0900")
            .setBatteryLevel(100)
            .enable()

        composeTestRule.setContent {
            SettingsView(onNavigateUp = { }, onDevContact = {})
        }
    }

    @Test
    fun appNavHost_verifyResources() {
        composeTestRule.onRoot().printToLog("TAG")

        //About section
        composeTestRule
            .onNodeWithContentDescription(
                context.getString(R.string.cd_about),
                useUnmergedTree = true
            )
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(
                context.getString(R.string.about_app_description),
                useUnmergedTree = true
            )
            .assertIsDisplayed()

        //Version preference
        composeTestRule
            .onNodeWithText(context.getString(R.string.version), useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(BuildConfig.VERSION_NAME, useUnmergedTree = true)
            .assertIsDisplayed()

        //Contact preference
        composeTestRule
            .onNodeWithText(context.getString(R.string.contact_developer), useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(
                context.getString(R.string.suggestions_problems),
                useUnmergedTree = true
            )
            .assertIsDisplayed()

        Screengrab.screenshot("settings_activity")
    }
}