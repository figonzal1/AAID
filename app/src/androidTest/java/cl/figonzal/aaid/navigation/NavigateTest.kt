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
 * Last modified: 15-12-22 22:59
 */

package cl.figonzal.aaid.navigation

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.navigation.NavigationItem
import cl.figonzal.aaid.ui.screens.main.AAIDViewModel
import cl.figonzal.aaid.utils.FakeAppNavHost
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigateTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setupAppNavHost() {

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            val viewModel: AAIDViewModel = viewModel()
            FakeAppNavHost(navController = navController, viewModel)
        }
    }

    @Test
    fun appNavHost_navigateToSettings() {

        //composeTestRule.onRoot().printToLog("TAG")

        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.cd_settings_button))
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        Truth.assertThat(route).isEqualTo(NavigationItem.SettingsNavItem.baseRoute)
    }
}