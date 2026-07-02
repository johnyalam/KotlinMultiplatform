package fi.developer.kotlinmultiplatform.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fi.developer.kotlinmultiplatform.di.appModules
import fi.developer.kotlinmultiplatform.presentation.viewmodel.coin.CoinViewModel
import fi.developer.kotlinmultiplatform.ui.screens.coins.CoinListScreen
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(appModules) }),
        content = {
            MaterialTheme {
                CoinListScreen()
            }
        })
}