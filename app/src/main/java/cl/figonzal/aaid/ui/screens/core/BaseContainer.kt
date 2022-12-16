package cl.figonzal.aaid.ui.screens.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.figonzal.aaid.ui.theme.AAIDTheme

@Composable
fun BaseContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    AAIDTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background
        ) { content() }
    }
}