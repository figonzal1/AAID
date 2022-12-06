package cl.figonzal.aaid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.figonzal.aaid.ui.AAIDViewModel
import cl.figonzal.aaid.ui.MainScreen
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: AAIDViewModel = viewModel()
            viewModel.requestAAID(LocalContext.current, Dispatchers.IO)
            MainScreen(viewModel)
        }
    }
}