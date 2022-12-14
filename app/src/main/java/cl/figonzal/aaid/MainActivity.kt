package cl.figonzal.aaid

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.figonzal.aaid.ui.main.AAIDViewModel
import cl.figonzal.aaid.ui.main.MainScreen
import cl.figonzal.aaid.ui.navigation.NavigationItem.*
import cl.figonzal.aaid.ui.settings.SettingsView
import cl.figonzal.aaid.utils.toast
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalAnimationApi::class)
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
                        onDevContact = {
                            contactIntent()
                        }
                    )
                }
            }
        }
    }

    private fun contactIntent() {
        Intent(ACTION_SEND).apply {
            putExtra(EXTRA_EMAIL, arrayOf(getString(R.string.mail_to_felipe)))
            putExtra(EXTRA_SUBJECT, getString(R.string.email_subject))
            type = "text/plain"

            when {
                resolveActivity(packageManager) != null -> {
                    startActivity(createChooser(this, getString(R.string.email_chooser_title)))
                }
                else -> toast(R.string.email_intent_fail)
            }
        }
    }
}