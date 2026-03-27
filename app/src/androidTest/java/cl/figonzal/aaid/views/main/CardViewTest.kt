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
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import cl.figonzal.aaid.BuildConfig
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.screens.main.AaidState
import cl.figonzal.aaid.ui.screens.main.CardAAID
import cl.figonzal.aaid.utils.TestConstants.FAKE_AAID
import org.junit.Rule
import org.junit.Test

class CardViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun cardView_successState_verifyResources() {
        composeTestRule.setContent {
            CardAAID(state = AaidState.Success(FAKE_AAID)) { }
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.cv_title))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.cv_subtitle))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("v${BuildConfig.VERSION_NAME}")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.cd_background_image))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.cd_settings_button))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_copy))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_share))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(FAKE_AAID)
            .assertIsDisplayed()
    }

    @Test
    fun cardView_loadingState_hidesActionButtons() {
        composeTestRule.setContent {
            CardAAID(state = AaidState.Loading) { }
        }

        // Header always visible
        composeTestRule
            .onNodeWithText(context.getString(R.string.cv_title))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.cd_settings_button))
            .assertIsDisplayed()

        // Action buttons must not be shown while loading
        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_copy))
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_share))
            .assertDoesNotExist()
    }

    @Test
    fun cardView_errorState_showsErrorMessageAndHidesActionButtons() {
        val errorMessage = "GMS not available"

        composeTestRule.setContent {
            CardAAID(state = AaidState.Error(errorMessage)) { }
        }

        // Header always visible
        composeTestRule
            .onNodeWithText(context.getString(R.string.cv_title))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.cd_settings_button))
            .assertIsDisplayed()

        // Error message visible
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertIsDisplayed()

        // Action buttons must not be shown on error
        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_copy))
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_share))
            .assertDoesNotExist()
    }
}
