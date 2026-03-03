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
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import cl.figonzal.aaid.MainActivity
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.screens.main.AAIDViewModel
import cl.figonzal.aaid.utils.FakeAppNavHost
import cl.figonzal.aaid.utils.ScreengrabBaseTest
import cl.figonzal.aaid.utils.TestConstants.FAKE_AAID
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tools.fastlane.screengrab.Screengrab

class MainScreenTest : ScreengrabBaseTest() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val intentsRule = IntentsRule()

    private lateinit var navController: TestNavHostController

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setupAppNavHost() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            val viewModel: AAIDViewModel = viewModel()
            viewModel.requestAAID { FAKE_AAID }
            FakeAppNavHost(navController = navController, viewModel)
        }
    }

    @Test
    fun appNavHost_verifyResources() {
        composeTestRule.waitUntil(timeoutMillis = 3_000) {
            composeTestRule
                .onAllNodesWithText(context.getString(R.string.btn_copy))
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

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
        composeTestRule.waitUntil(timeoutMillis = 3_000) {
            composeTestRule
                .onAllNodesWithText(context.getString(R.string.btn_share))
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_share))
            .assertIsDisplayed()
            .performClick()

        composeTestRule.waitForIdle()

        val expectedIntent = allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(
                equalTo(Intent.EXTRA_INTENT),
                allOf(
                    hasAction(Intent.ACTION_SEND),
                ),
            ),
            hasExtra(
                `is`(Intent.EXTRA_TITLE),
                `is`(context.getString(R.string.intent_chooser)),
            ),
        )

        intended(expectedIntent)

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()
    }
}
