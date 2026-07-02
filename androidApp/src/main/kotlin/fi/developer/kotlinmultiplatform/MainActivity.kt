package fi.developer.kotlinmultiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fi.developer.kotlinmultiplatform.presentation.viewmodel.coin.CoinViewModel
import fi.developer.kotlinmultiplatform.ui.screens.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val viewModel = CoinViewModel()
        setContent {
            App(viewModel)
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    val viewModel = CoinViewModel()
    App(viewModel)
}