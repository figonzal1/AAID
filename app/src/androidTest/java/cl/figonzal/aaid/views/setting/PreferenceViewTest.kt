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

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import cl.figonzal.aaid.ui.screens.settings.Preference
import org.junit.Rule
import org.junit.Test

class PreferenceViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun appNavHost_verifyResources_whenTitleIsPresent() {

        composeTestRule.setContent {
            Preference(title = "Test title", subTitle = "test subtitle", isTitlePresent = true) {}
        }

        composeTestRule.onRoot().printToLog("TAG")

        //About section
        composeTestRule
            .onNodeWithText("test subtitle")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Test title")
            .assertIsDisplayed()

    }

    @Test
    fun appNavHost_verifyResources_whenTitleIsAbsent() {

        composeTestRule.setContent {
            Preference(title = "Test title", subTitle = "test subtitle", isTitlePresent = false) {}
        }

        composeTestRule.onRoot().printToLog("TAG")

        //About section
        composeTestRule
            .onNodeWithText("test subtitle")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Test title")
            .assertDoesNotExist()

    }
}