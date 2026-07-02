package fi.developer.kotlinmultiplatform.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import fi.developer.kotlinmultiplatform.presentation.viewmodel.coin.CoinViewModel
import fi.developer.kotlinmultiplatform.ui.screens.coins.CoinListScreen

@Composable
fun App(viewModel: CoinViewModel) {
    MaterialTheme {
        CoinListScreen(viewModel)
    }
}