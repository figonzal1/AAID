package cl.figonzal.aaid

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.figonzal.aaid.ui.main.AAIDViewModel
import cl.figonzal.aaid.ui.main.MainScreen
import cl.figonzal.aaid.ui.navigation.NavigationItem
import cl.figonzal.aaid.ui.settings.SettingsView
import cl.figonzal.aaid.utils.contactIntent

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