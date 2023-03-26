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

@file:Suppress("TestFunctionName")

package cl.figonzal.aaid.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.figonzal.aaid.ui.navigation.NavigationItem
import cl.figonzal.aaid.ui.screens.main.AAIDViewModel
import cl.figonzal.aaid.ui.screens.main.MainScreen
import cl.figonzal.aaid.ui.screens.settings.SettingsView

@Composable
fun FakeAppNavHost(
    navController: NavHostController,
    viewModel: AAIDViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.MainNavItem.baseRoute
    ) {

        with(navController) {
            fakeMainScreenComposable(viewModel, this)
            fakeSettingsScreenComposable(this)
        }
    }
}

fun NavGraphBuilder.fakeSettingsScreenComposable(navController: NavHostController) {

    composable(
        NavigationItem.SettingsNavItem.baseRoute
    ) {
        SettingsView(
            onNavigateUp = { navController.navigateUp() },
            onDevContact = { navController.context.contactIntent() }
        )
    }
}

fun NavGraphBuilder.fakeMainScreenComposable(
    viewModel: AAIDViewModel,
    navController: NavHostController
) {
    composable(
        NavigationItem.MainNavItem.baseRoute
    ) {
        MainScreen(
            viewModel,
            onSettingsClick = { navController.navigate(NavigationItem.SettingsNavItem.baseRoute) })
    }
}