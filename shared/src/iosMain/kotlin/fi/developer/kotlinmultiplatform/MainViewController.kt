package fi.developer.kotlinmultiplatform

import androidx.compose.ui.window.ComposeUIViewController
import fi.developer.kotlinmultiplatform.viewmodel.GameViewModel
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    App(GameViewModel())
}