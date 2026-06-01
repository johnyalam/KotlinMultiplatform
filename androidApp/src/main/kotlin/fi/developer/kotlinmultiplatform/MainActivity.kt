package fi.developer.kotlinmultiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fi.developer.kotlinmultiplatform.model.WordItem
import fi.developer.kotlinmultiplatform.ui.WordGameScreen
import fi.developer.kotlinmultiplatform.utils.MockData.Companion.sampleWords
import fi.developer.kotlinmultiplatform.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val viewModel = GameViewModel(sampleWords)
        setContent {
            WordGameScreen(viewModel)
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    val viewModel = GameViewModel(sampleWords)
    WordGameScreen(viewModel)
}