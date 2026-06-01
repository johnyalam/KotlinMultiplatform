package fi.developer.kotlinmultiplatform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import fi.developer.kotlinmultiplatform.ui.WordGameScreen
import fi.developer.kotlinmultiplatform.viewmodel.GameViewModel

@Composable
fun App(viewModel: GameViewModel) {
    MaterialTheme {
        WordGameScreen(viewModel)
    }
}