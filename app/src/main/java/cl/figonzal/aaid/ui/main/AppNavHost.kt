package cl.figonzal.aaid.ui.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import cl.figonzal.aaid.ui.navigation.NavigationItem
import cl.figonzal.aaid.ui.settings.SettingsView
import cl.figonzal.aaid.utils.contactIntent
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

private const val ANIMATION_DURATION = 300

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: AAIDViewModel
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = NavigationItem.MainNavItem.baseRoute
    ) {

        with(navController) {
            mainScreenComposable(viewModel, this)
            settingsScreenComposable(this)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsScreenComposable(navController: NavHostController) {

    composable(
        NavigationItem.SettingsNavItem.baseRoute,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
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

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainScreenComposable(
    viewModel: AAIDViewModel,
    navController: NavHostController
) {
    composable(
        NavigationItem.MainNavItem.baseRoute,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            )
        }
    ) {
        MainScreen(
            viewModel,
            onSettingsClick = { navController.navigate(NavigationItem.SettingsNavItem.baseRoute) })
    }
}