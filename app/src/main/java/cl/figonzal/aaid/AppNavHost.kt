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
 * Last modified: 18-12-22 11:40
 */

package cl.figonzal.aaid

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.figonzal.aaid.ui.navigation.NavigationItem
import cl.figonzal.aaid.ui.screens.main.AAIDViewModel
import cl.figonzal.aaid.ui.screens.main.MainScreen
import cl.figonzal.aaid.ui.screens.settings.SettingsView
import cl.figonzal.aaid.utils.contactIntent

private const val ANIMATION_DURATION = 300

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: AAIDViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.MainNavItem.baseRoute
    ) {

        with(navController) {
            mainScreenComposable(viewModel, this)
            settingsScreenComposable(this)
        }
    }
}

fun NavGraphBuilder.settingsScreenComposable(navController: NavHostController) {

    composable(
        NavigationItem.SettingsNavItem.baseRoute,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION)
            )
        }
    ) {
        SettingsView(
            onNavigateUp = { navController.navigateUp() },
            onDevContact = { navController.context.contactIntent() }
        )
    }
}

fun NavGraphBuilder.mainScreenComposable(
    viewModel: AAIDViewModel,
    navController: NavHostController
) {
    composable(
        NavigationItem.MainNavItem.baseRoute,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            )
        }
    ) {
        MainScreen(
            viewModel,
            onSettingsClick = { navController.navigate(NavigationItem.SettingsNavItem.baseRoute) })
    }
}