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
 * Last modified: 15-12-22 22:46
 */

package cl.figonzal.aaid.views.main

import android.content.Context
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import cl.figonzal.aaid.FakeAppNavHost
import cl.figonzal.aaid.MainActivity
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.screens.main.AAIDViewModel
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy
import tools.fastlane.screengrab.cleanstatusbar.BluetoothState
import tools.fastlane.screengrab.cleanstatusbar.CleanStatusBar
import tools.fastlane.screengrab.cleanstatusbar.MobileDataType
import tools.fastlane.screengrab.locale.LocaleTestRule


class MainScreenTest {

    @Rule
    @JvmField
    val localeTestRule = LocaleTestRule()

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    lateinit var navController: TestNavHostController

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

        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            val viewModel: AAIDViewModel = viewModel()
            FakeAppNavHost(navController = navController, viewModel)
        }
    }

    @Test
    fun appNavHost_verifyResources() {

        Thread.sleep(2000)

        //Buttons
        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_copy))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_share))
            .assertIsDisplayed()

        Screengrab.screenshot("main_activity")
    }

    @Test
    fun appNavHost_clickShareButton() {

        Thread.sleep(2000)

        Intents.init()

        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_share))
            .assertIsDisplayed()
            .performClick()

        val expectedIntent = allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(
                equalTo(Intent.EXTRA_INTENT),
                allOf(
                    hasAction(Intent.ACTION_SEND)
                )
            ),
            hasExtra(
                `is`(Intent.EXTRA_TITLE),
                `is`(context.getString(R.string.intent_chooser))
            )
        )

        intended(expectedIntent)

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()

        Intents.release()

        Thread.sleep(2000)
    }
}