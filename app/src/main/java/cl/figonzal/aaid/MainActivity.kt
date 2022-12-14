package cl.figonzal.aaid

import android.content.Intent.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import cl.figonzal.aaid.ui.main.AAIDViewModel
import cl.figonzal.aaid.ui.main.MainScreen
import cl.figonzal.aaid.ui.navigation.NavigationItem.*
import cl.figonzal.aaid.ui.settings.SettingsView
import cl.figonzal.aaid.utils.contactIntent
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.Dispatchers

private const val ANIMATION_DURATION = 300

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            val viewModel: AAIDViewModel = viewModel()
            viewModel.requestAAID(LocalContext.current, Dispatchers.IO)

            val navController = rememberAnimatedNavController()

            AnimatedNavHost(
                navController = navController,
                startDestination = MainNavItem.baseRoute
            ) {

                with(navController) {
                    mainScreenComposable(viewModel, this)
                    settingsScreenComposable(this)
                }
            }
        }
    }

    private fun NavGraphBuilder.settingsScreenComposable(navController: NavHostController) {
        composable(
            SettingsNavItem.baseRoute,
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
                onDevContact = { contactIntent() }
            )
        }
    }

    private fun NavGraphBuilder.mainScreenComposable(
        viewModel: AAIDViewModel,
        navController: NavHostController
    ) {
        composable(
            MainNavItem.baseRoute,
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
                onSettingsClick = { navController.navigate(SettingsNavItem.baseRoute) })
        }
    }
}