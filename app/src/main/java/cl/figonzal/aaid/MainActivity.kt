package cl.figonzal.aaid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.figonzal.aaid.ui.AAIDViewModel
import cl.figonzal.aaid.ui.MainScreen
import cl.figonzal.aaid.ui.settings.SettingsView
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            val viewModel: AAIDViewModel = viewModel()
            viewModel.requestAAID(LocalContext.current, Dispatchers.IO)

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main_screen") {

                composable("main_screen") {
                    MainScreen(viewModel,
                        onSettingsClick = {
                            navController.navigate("settings")
                        })
                }
                composable("settings") {
                    SettingsView(
                        onNavigateUp = { navController.navigateUp() },
                        onDevContact = {}
                    )
                }
            }
        }
    }
}