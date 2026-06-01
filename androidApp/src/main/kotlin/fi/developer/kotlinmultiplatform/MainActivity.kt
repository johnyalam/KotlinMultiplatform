package fi.developer.kotlinmultiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fi.developer.kotlinmultiplatform.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val viewModel = GameViewModel()
        setContent {
            App(viewModel)
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    val viewModel = GameViewModel()
    App(viewModel)
}