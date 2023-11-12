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

package cl.figonzal.aaid.views.setting

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Abc
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import cl.figonzal.aaid.ui.screens.settings.PreferenceCategory
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PreferenceCategoryViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setupAppNavHost() {

        composeTestRule.setContent {
            PreferenceCategory(
                title = "Test title",
                contentDescription = "Icon content",
                icon = Icons.Rounded.Abc
            )
        }
    }

    @Test
    fun appNavHost_verifyResources() {

        Thread.sleep(4000)

        composeTestRule.onRoot().printToLog("TAG")

        //About section
        composeTestRule
            .onNodeWithContentDescription("Icon content")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Test title")
            .assertIsDisplayed()

    }

}